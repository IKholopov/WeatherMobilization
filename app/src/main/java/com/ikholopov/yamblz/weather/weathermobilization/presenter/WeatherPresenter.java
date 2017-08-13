package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.util.Log;

import com.ikholopov.yamblz.weather.weathermobilization.SchedulerProvider;
import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.model.Forecast;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.WeatherService;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.CityRepository;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.WeatherRepository;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.TemperatureFormat;
import com.ikholopov.yamblz.weather.weathermobilization.model.services.WeatherUpdater;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.Weather;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.WeatherView;

import org.threeten.bp.Instant;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;


/**
 * Created by igor on 7/16/17.
 */

public class WeatherPresenter extends RxPresenter {

    final SchedulerProvider schedulers;
    final WeatherRepository repository;
    final CityRepository cityRepository;
    final WeatherUpdater updater;
    final WeatherService weatherService;
    final PreferencesProvider preferences;

    WeatherView weatherView;
    City city;
    Disposable currentSubscription;

    @Inject
    public WeatherPresenter(SchedulerProvider schedulers, WeatherRepository repository,
                            CityRepository cityRepository, WeatherUpdater updater,
                            WeatherService weatherService, PreferencesProvider preferences) {
        this.schedulers = schedulers;
        this.repository = repository;
        this.cityRepository = cityRepository;
        this.updater = updater;
        this.weatherService = weatherService;
        this.preferences = preferences;
    }

    public void bind(final WeatherView weatherView, final City city) {
        this.city = city;
        this.weatherView = weatherView;

        weatherView.setLocation(city.name);

        subscribeOnStorage();
        updateWeather();

        save(weatherView.refreshes()
                .observeOn(schedulers.io())
                .subscribe(o -> updateWeather(), throwable -> {
                        weatherView.onError();
                        Log.e("WeatherPresenter", "when updating", throwable);
                }));
    }

    @Override
    public void unbind() {
        super.unbind();
        weatherView = null;
    }

    void subscribeOnStorage() {
        save(currentSubscription = repository.forCity(city)
                .subscribeOn(schedulers.io())
                .flatMapSingle(this::filterOldAndMapToView)
                .observeOn(schedulers.main())
                .subscribe(weatherView, throwable -> {
                    weatherView.onError();
                    Log.e("WeatherPresenter", "when get weather", throwable);
                }));
    }

    void updateWeather() {
        cityRepository.getByPlaceId(city.placeId)
                .subscribeOn(schedulers.io())
                .defaultIfEmpty(city)
                .subscribe(byPlaceId -> {
                    if(byPlaceId.id > 0) {

                        if(byPlaceId.id != city.id) {
                            currentSubscription.dispose();
                            city = byPlaceId;
                            subscribeOnStorage();
                        }

                        updater.updateWeather(city);

                        return;
                    }

                    save(weatherService.forecasts(city).toList()
                            .flatMap(this::filterOldAndMapToView)
                            .observeOn(schedulers.main())
                            .subscribe(weatherView, throwable -> {
                                weatherView.onError();
                                Log.e("WeatherPresenter", "when get weather", throwable);
                            }));

                }, throwable -> {
                    weatherView.onError();
                    Log.e("WeatherPresenter", "when check city", throwable);
                });
    }

    Single<List<Weather>> filterOldAndMapToView(List<Forecast> forecasts) {
        Instant limit = Instant.now().minusSeconds(3600);
        boolean isCelsius = preferences.getTemperatureFormat() == TemperatureFormat.CELSIUS;
        return Observable.fromIterable(forecasts)
                .filter(forecast -> forecast.dateTime.isAfter(limit))
                .map(forecast -> new Weather(forecast, isCelsius))
                .subscribeOn(schedulers.computation())
                .toList();
    }
}