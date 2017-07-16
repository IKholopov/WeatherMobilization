package com.ikholopov.yamblz.weather.weathermobilization.di.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by igor on 7/16/17.
 */

@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }
}
