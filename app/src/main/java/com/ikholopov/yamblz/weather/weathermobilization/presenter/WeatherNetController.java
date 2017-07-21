package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeatherLoader;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.ForecastFragment;

public class WeatherNetController implements LoaderNetController {

    private static final int CURRENT_WEATHER_LOADER_ID = 0;

    private ForecastFragment weatherFragment;
    private CurrentWeatherLoader loader;

    public WeatherNetController() {
        weatherFragment = null;
    }

    @Override
    public void bindForecastFragment(@NonNull ForecastFragment fragment) {
        this.weatherFragment = fragment;
        loader = new CurrentWeatherLoader(fragment.getActivityAttachedTo());
        weatherFragment.getActivityAttachedTo().getSupportLoaderManager()
                .initLoader(CURRENT_WEATHER_LOADER_ID, null, this).forceLoad();
    }

    @Override
    public void forceNetLoad() {
        loader.forceNetLoad();
    }

    @Override
    public Loader<CurrentWeather> onCreateLoader(int id, Bundle args) {
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<CurrentWeather> loader, CurrentWeather data) {
        if(weatherFragment == null) {
            throw new IllegalStateException("WeatherNetController not binded!");
        }
        weatherFragment.setWeather(data);
    }

    @Override
    public void onLoaderReset(Loader<CurrentWeather> loader) {
    }
}
