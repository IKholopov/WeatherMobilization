package com.ikholopov.yamblz.weather.weathermobilization.presenter.views;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by igor on 7/20/17.
 */

public interface WeatherView extends Consumer<List<Weather>> {

    Observable<Object> refreshes();
    void setLocation(String name);

    void onError();
}
