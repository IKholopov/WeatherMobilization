package com.ikholopov.yamblz.weather.weathermobilization.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ikholopov.yamblz.weather.weathermobilization.presenter.LoaderNetController;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.UpdateServiceController;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.WeatherNetController;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.WeatherServiceController;

import dagger.Module;
import dagger.Provides;

/**
 * Provides application
 * Created by igor on 7/16/17.
 */

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    Context provideContext() {
        return  application.getApplicationContext();
    }

    @Provides
    UpdateServiceController provideServiceController() {
        return new WeatherServiceController();
    }

    @Provides
    LoaderNetController provideLoaderNetController() {
        return new WeatherNetController();
    }
}
