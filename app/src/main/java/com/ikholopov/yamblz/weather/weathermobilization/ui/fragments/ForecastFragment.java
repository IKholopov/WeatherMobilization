package com.ikholopov.yamblz.weather.weathermobilization.ui.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;

/**
 * Created by igor on 7/20/17.
 */

public interface ForecastFragment {
    FragmentActivity getActivityAttachedTo();
    //Update weather
    void setWeather(@Nullable CurrentWeather weather);
}
