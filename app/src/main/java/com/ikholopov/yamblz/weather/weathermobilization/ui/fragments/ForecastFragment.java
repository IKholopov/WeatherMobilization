package com.ikholopov.yamblz.weather.weathermobilization.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;

/**
 * Created by igor on 7/20/17.
 */

public interface ForecastFragment {

    Loader<CurrentWeather> initLoader(int id, Bundle args, LoaderManager.LoaderCallbacks<CurrentWeather> callbacks);

    void setWeather(@Nullable CurrentWeather weather);
}
