package com.ikholopov.yamblz.weather.weathermobilization.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by igor on 7/16/17.
 */

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }
}
