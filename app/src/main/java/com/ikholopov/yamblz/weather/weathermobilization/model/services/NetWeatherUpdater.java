package com.ikholopov.yamblz.weather.weathermobilization.model.services;

import android.support.annotation.WorkerThread;
import android.util.Log;

import com.ikholopov.yamblz.weather.weathermobilization.SchedulerProvider;
import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.WeatherService;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.CityRepository;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.WeatherRepository;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.db.AppDatabase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by turist on 13.08.2017.
 */

public class NetWeatherUpdater implements WeatherUpdater {

    final WeatherRepository weatherRepository;
    final CityRepository cityRepository;
    final WeatherService weatherService;
    final SchedulerProvider schedulers;

    @Inject
    public NetWeatherUpdater(WeatherRepository weatherRepository, CityRepository cityRepository,
                             WeatherService weatherService, SchedulerProvider schedulers) {
        this.weatherRepository = weatherRepository;
        this.cityRepository = cityRepository;
        this.weatherService = weatherService;
        this.schedulers = schedulers;
    }

    @Override
    public void updateWeather() {
        Observable.fromArray(cityRepository.blockingAll())
                .subscribeOn(schedulers.computation())
                .subscribe(
                        this::updateWeather,
                        throwable -> Log.e("NetWeatherUpdater", "updateWeather", throwable));
    }

    @Override
    @WorkerThread
    public void updateWeather(City city) {
        weatherService.forecasts(city)
                .toList()
                .subscribe(
                        forecasts -> {
                            weatherRepository.deleteForCity(city);
                            weatherRepository.save(forecasts);
                        },
                        throwable ->
                            Log.e("NetWeatherUpdater", "updateWeather(city)", throwable
                        )
                );
    }
}