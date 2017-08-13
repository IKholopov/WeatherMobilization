package com.ikholopov.yamblz.weather.weathermobilization.di.component;

import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.OnBoot;
import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.application.ApplicationModule;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.application.GooglePlacesModule;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.application.OpenWeatherMapModule;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.application.PreferencesModule;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.application.RoomModule;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.application.UpdateModule;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.PlacesService;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.WeatherService;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.model.services.UpdateServiceController;
import com.ikholopov.yamblz.weather.weathermobilization.model.services.WeatherUpdateService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Provides application context and preferences
 * Created by igor on 7/16/17.
 */

@Singleton
@Component(modules = { ApplicationModule.class, UpdateModule.class,
                       GooglePlacesModule.class, OpenWeatherMapModule.class,
                       PreferencesModule.class, RoomModule.class })
public interface ApplicationComponent {

    void inject(OnBoot onBoot);
    void inject(WeatherApplication application);
    void inject(WeatherUpdateService updateService);

    PreferencesProvider getPreferencesProvider();
    UpdateServiceController getServiceController();
    PlacesService getPlacesService();
    WeatherService getWeatherService();
    Context getContext();

    ActivityComponent.Builder activityComponentBuilder();
}