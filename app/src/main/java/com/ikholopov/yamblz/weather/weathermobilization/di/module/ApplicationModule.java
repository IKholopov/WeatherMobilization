package com.ikholopov.yamblz.weather.weathermobilization.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

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
}
