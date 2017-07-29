package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.ForecastFragment;

import javax.inject.Inject;

/**
 * Created by igor on 7/16/17.
 */

public class CurrentWeatherPresenterImpl implements CurrentWeatherPresenter {

    private PreferencesProvider preferences;
    private UpdateServiceController serviceController;
    private LoaderNetController weatherNetController;

    @Inject
    public CurrentWeatherPresenterImpl(PreferencesProvider preferences,
           UpdateServiceController serviceController, LoaderNetController weatherNetController){
        this.preferences = preferences;
        this.serviceController = serviceController;
        this.weatherNetController = weatherNetController;
    }

    @Override
    public void bind(ForecastFragment weatherFragment) {
        weatherNetController.bindForecastFragment(weatherFragment);
        if(preferences.getAutoupdateEnabledPreference()) {
            serviceController.enableService(preferences.getUpdateInterval());
        }
    }

    @Override
    public void forceReload() {
        weatherNetController.forceNetLoad();
    }
}

