package com.ikholopov.yamblz.weather.weathermobilization.di.module.application;

import com.ikholopov.yamblz.weather.weathermobilization.model.network.PlacesService;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.api.GooglePlacesService;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by turist on 28.07.2017.
 */

@Module(includes = GoogleApiModule.class)
@Singleton
public abstract class GooglePlacesModule {

    @Binds
    @Singleton
    abstract PlacesService provideService(GooglePlacesService service);
}