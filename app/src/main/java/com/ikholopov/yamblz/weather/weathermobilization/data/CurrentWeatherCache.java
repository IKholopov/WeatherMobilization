package com.ikholopov.yamblz.weather.weathermobilization.data;

import android.support.annotation.Nullable;

import java.io.IOException;

import io.reactivex.functions.Consumer;

/**
 * Created by turist on 25.07.2017.
 */

public interface CurrentWeatherCache extends Consumer<CurrentWeather> {

    @Nullable
    CurrentWeather load();

    void clear() throws IOException;
}
