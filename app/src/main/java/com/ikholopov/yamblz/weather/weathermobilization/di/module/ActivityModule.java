package com.ikholopov.yamblz.weather.weathermobilization.di.module;

import com.ikholopov.yamblz.weather.weathermobilization.ui.NavigatableActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Provides activity
 * Created by igor on 7/16/17.
 */

@Module
public class ActivityModule {
    private NavigatableActivity activity;

    public ActivityModule(NavigatableActivity activity) {
        this.activity = activity;
    }

    @Provides
    NavigatableActivity provideActivity() {
        return activity;
    }
}
