package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;

import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.ForecastFragment;

/**
 * Responsible for Loader initialization and handling requests to it
 * Created by igor on 7/21/17.
 */

public interface LoaderNetController extends LoaderManager.LoaderCallbacks<CurrentWeather> {
    void bindForecastFragment(@NonNull ForecastFragment fragment);
    void forceNetLoad();
}
