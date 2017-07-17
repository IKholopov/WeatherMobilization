package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;

import com.ikholopov.yamblz.weather.weathermobilization.WeatherUpdateService;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeatherLoader;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.WeatherFragment;

/**
 * Created by igor on 7/16/17.
 */

public class CurrentWeatherPresenterImpl implements CurrentWeatherPresenter, LoaderManager.LoaderCallbacks<CurrentWeather> {

    private WeatherFragment weatherFragment;
    private CurrentWeatherLoader loader;

    private final int CURRENT_WEATHER_LOADER_ID = 0;

    @Override
    public void bind(WeatherFragment weatherFragment) {
        this.weatherFragment = weatherFragment;
        loader = new CurrentWeatherLoader(this.weatherFragment.getContext());
        weatherFragment.getActivity().getSupportLoaderManager()
                .initLoader(CURRENT_WEATHER_LOADER_ID, null, this).forceLoad();
        if(PreferencesProvider.getAutoupdateEnabledPreference(weatherFragment.getContext())) {
            WeatherUpdateService.setServiceEnabled(weatherFragment.getContext(), true,
                    PreferencesProvider.getUpdateInterval(weatherFragment.getContext()));
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
