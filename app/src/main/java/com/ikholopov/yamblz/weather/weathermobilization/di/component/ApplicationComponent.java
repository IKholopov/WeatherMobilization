package com.ikholopov.yamblz.weather.weathermobilization.di.component;

import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.OnBoot;
import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeatherCache;
import com.ikholopov.yamblz.weather.weathermobilization.data.PlacesService;
import com.ikholopov.yamblz.weather.weathermobilization.data.WeatherService;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.ApplicationModule;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.GoogleApiModule;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.OpenWeatherMapApiModule;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.PreferencesProviderModule;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.UpdateServiceController;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.WeatherNetController;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.SettingsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Provides application context and preferences
 * Created by igor on 7/16/17.
 */

@Singleton
@Component(modules = { ApplicationModule.class, PreferencesProviderModule.class,
        GoogleApiModule.class, OpenWeatherMapApiModule.class })
public interface ApplicationComponent {
    void inject(WeatherApplication application);
    void inject(SettingsFragment fragment);
    void inject(OnBoot receiver);

    PreferencesProvider getPreferencesProvider();
    UpdateServiceController getServiceController();
    WeatherNetController getWeatherNetController();
    PlacesService getPlacesService();
    WeatherService getWeatherService();
    CurrentWeatherCache getWeatherCache();
    Context getContext();
}