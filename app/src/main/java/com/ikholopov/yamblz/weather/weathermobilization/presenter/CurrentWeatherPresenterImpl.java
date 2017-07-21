package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.os.Bundle;

import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;
import com.ikholopov.yamblz.weather.weathermobilization.WeatherUpdateService;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeatherLoader;
import com.ikholopov.yamblz.weather.weathermobilization.di.component.ApplicationComponent;
import com.ikholopov.yamblz.weather.weathermobilization.di.component.ViewComponent;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.ForecastFragment;

import javax.inject.Inject;

/**
 * Created by igor on 7/16/17.
 */

public class CurrentWeatherPresenterImpl implements CurrentWeatherPresenter {


    @Inject PreferencesProvider preferences;
    @Inject UpdateServiceController serviceController;
    @Inject LoaderNetController weatherNetController;

    @Override
    public void bind(ForecastFragment weatherFragment) {
        ApplicationComponent component = WeatherApplication.get(weatherFragment
                .getActivityAttachedTo()).getComponent();
        component.inject(this);
        weatherNetController.bindForecastFragment(weatherFragment);
        serviceController.bindComponent(component);
        if(preferences.getAutoupdateEnabledPreference()) {
            serviceController.enableService(preferences.getUpdateInterval());
        }
    }

    @Override
    public void forceReload() {
        weatherNetController.forceNetLoad();
    }
}

