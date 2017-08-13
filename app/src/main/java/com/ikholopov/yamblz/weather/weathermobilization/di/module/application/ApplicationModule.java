package com.ikholopov.yamblz.weather.weathermobilization.di.module.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ikholopov.yamblz.weather.weathermobilization.di.component.ActivityComponent;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.LanguageProvider;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.LocalLanguageProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides application
 * Created by igor on 7/16/17.
 */

@Singleton
@Module(subcomponents = { ActivityComponent.class })
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    LanguageProvider provideLanguageProvider() {
        return new LocalLanguageProvider();
    }
}
