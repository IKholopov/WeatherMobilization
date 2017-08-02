package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.support.annotation.Nullable;

import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeatherCache;
import com.ikholopov.yamblz.weather.weathermobilization.data.WeatherService;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class WeatherNetController {

    private final WeatherService service;
    private final CurrentWeatherCache cache;
    private final PreferencesProvider preferences;
    private final Subject<CurrentWeather> currentWeatherSubject;

    @Inject
    public WeatherNetController(WeatherService service, CurrentWeatherCache cache,
                                PreferencesProvider preferences) {
        this.service = service;
        this.cache = cache;
        this.preferences = preferences;

        CurrentWeather loaded = this.cache.load();
        if(loaded != null) {
            this.currentWeatherSubject = BehaviorSubject.createDefault(loaded);
        } else {
            this.currentWeatherSubject = BehaviorSubject.create();
        }
    }

    @Nullable
    public Disposable refresh() {
        CurrentWeather loaded = cache.load();
        if(loaded != null) {
            currentWeatherSubject.onNext(loaded);
            return null;
        } else {
            return forceNetLoad();
        }
    }

    public Disposable forceNetLoad() {
        return service.currentWeather(preferences.getCityLat(), preferences.getCityLng())
                .subscribeOn(Schedulers.io())
                .doOnSuccess(cache)
                .subscribe(new Consumer<CurrentWeather>() {
                    @Override
                    public void accept(@NonNull CurrentWeather weather) throws Exception {
                        currentWeatherSubject.onNext(weather);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        currentWeatherSubject.onError(throwable);
                    }
                });
    }

    public Observable<CurrentWeather> currentWeather() {
        return currentWeatherSubject.distinctUntilChanged();
    }
}
