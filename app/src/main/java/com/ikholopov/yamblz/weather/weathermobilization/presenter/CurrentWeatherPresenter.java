package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.util.Log;

import com.ikholopov.yamblz.weather.weathermobilization.data.WeatherMapper;
import com.ikholopov.yamblz.weather.weathermobilization.ui.views.WeatherView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by igor on 7/16/17.
 */

public class CurrentWeatherPresenter extends RxPresenter {

    private final WeatherNetController controller;
    private final WeatherMapper mapper;

    @Inject
    public CurrentWeatherPresenter(WeatherNetController controller, WeatherMapper mapper) {
        this.controller = controller;
        this.mapper = mapper;
    }

    public void refresh() {
        Disposable d = controller.refresh();
        if(d != null) {
            save(d);
        }
    }

    public void bind(final WeatherView weatherView) {
        save(controller.currentWeather()
                .subscribeOn(Schedulers.computation())
                .map(mapper)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherView, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        weatherView.onError();
                        Log.e("CurrentWeatherPresenter", "when updating", throwable);
                    }
                }));

        save(weatherView.refreshes()
                .subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                save(controller.forceNetLoad());
            }
        }));
    }
}