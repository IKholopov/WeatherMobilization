package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;

import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;
import com.ikholopov.yamblz.weather.weathermobilization.WeatherUpdateService;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeatherLoader;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.ForecastFragment;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.WeatherFragment;

import javax.inject.Inject;

/**
 * Created by igor on 7/16/17.
 */

public class CurrentWeatherPresenterImpl implements CurrentWeatherPresenter, LoaderManager.LoaderCallbacks<CurrentWeather> {

    private ForecastFragment weatherFragment;
    private CurrentWeatherLoader loader;

    @Inject PreferencesProvider preferences;

    private final int CURRENT_WEATHER_LOADER_ID = 0;

    @Override
    public void bind(ForecastFragment weatherFragment) {
        this.weatherFragment = weatherFragment;
        WeatherApplication.get(weatherFragment.getActivityAttachedTo()).getComponent().inject(this);
        loader = new CurrentWeatherLoader(this.weatherFragment.getActivityAttachedTo());
        weatherFragment.getActivityAttachedTo().getSupportLoaderManager()
                .initLoader(CURRENT_WEATHER_LOADER_ID, null, this).forceLoad();
        if(preferences.getAutoupdateEnabledPreference()) {
            WeatherUpdateService.setServiceEnabled(weatherFragment.getActivityAttachedTo(), true,
                    preferences.getUpdateInterval());
        }
    }

    @Override
    public void forceReload() {
        loader.forceNetLoad();
    }

    @Override
    public Loader<CurrentWeather> onCreateLoader(int id, Bundle args) {
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<CurrentWeather> loader, CurrentWeather data) {
        weatherFragment.setWeather(data);
    }

    @Override
    public void onLoaderReset(Loader<CurrentWeather> loader) {
    }
}
