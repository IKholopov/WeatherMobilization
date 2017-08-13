package com.ikholopov.yamblz.weather.weathermobilization.di.module.application;

import com.ikholopov.yamblz.weather.weathermobilization.model.services.NetWeatherUpdater;
import com.ikholopov.yamblz.weather.weathermobilization.model.services.UpdateServiceController;
import com.ikholopov.yamblz.weather.weathermobilization.model.services.WeatherUpdateServiceController;
import com.ikholopov.yamblz.weather.weathermobilization.model.services.WeatherUpdater;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by turist on 07.08.2017.
 */

@Module
@Singleton
public abstract class UpdateModule {

    @Binds
    @Singleton
    abstract UpdateServiceController provideServiceController(WeatherUpdateServiceController controller);

    @Binds
    @Singleton
    abstract WeatherUpdater provideUpdater(NetWeatherUpdater updater);
}