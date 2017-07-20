package com.ikholopov.yamblz.weather.weathermobilization.di.module;

import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Provides preferences wrapper
 * Created by igor on 7/20/17.
 */

@Module
public class PreferencesProviderModule {
    private final PreferencesProvider provider;

    public PreferencesProviderModule(Context context) {
        provider = new PreferencesProvider(context);
    }

    @Provides
    PreferencesProvider providePreferencesProvider() {
        return provider;
    }
}
