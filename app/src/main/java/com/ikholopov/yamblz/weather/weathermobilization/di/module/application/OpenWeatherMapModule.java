package com.ikholopov.yamblz.weather.weathermobilization.di.module.application;

import com.ikholopov.yamblz.weather.weathermobilization.model.network.WeatherService;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.api.OpenWeatherMapService;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by turist on 28.07.2017.
 */

@Module(includes = OpenWeatherMapApiModule.class)
@Singleton
public abstract class OpenWeatherMapModule {

    @Binds
    @Singleton
    abstract WeatherService provideService(OpenWeatherMapService service);
}