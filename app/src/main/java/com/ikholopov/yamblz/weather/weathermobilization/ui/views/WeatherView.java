package com.ikholopov.yamblz.weather.weathermobilization.ui.views;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by igor on 7/20/17.
 */

public interface WeatherView extends Consumer<Weather> {

    Observable<Object> refreshes();
    void onError();
}
