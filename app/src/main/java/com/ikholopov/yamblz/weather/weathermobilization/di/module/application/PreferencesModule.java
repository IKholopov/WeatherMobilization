package com.ikholopov.yamblz.weather.weathermobilization.di.module.application;

import android.content.Context;
import android.preference.PreferenceManager;

import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.PreferencesProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides preferences wrapper
 * Created by igor on 7/20/17.
 */

@Module
public class PreferencesModule {

    @Provides
    @Singleton
    PreferencesProvider providePreferencesProvider(Context context) {
        return new PreferencesProvider(PreferenceManager.getDefaultSharedPreferences(context));
    }
}
