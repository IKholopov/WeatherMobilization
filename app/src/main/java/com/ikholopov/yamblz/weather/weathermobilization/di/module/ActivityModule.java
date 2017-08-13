package com.ikholopov.yamblz.weather.weathermobilization.di.module;

import com.ikholopov.yamblz.weather.weathermobilization.presenter.MainActivityRouter;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.MainRouter;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.TwoPaneRouter;
import com.ikholopov.yamblz.weather.weathermobilization.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Provides activity
 * Created by igor on 7/16/17.
 */

@Module
public class ActivityModule {

    private final MainActivity activity;

    public ActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    MainActivity provideActivity() {
        return activity;
    }

    @Provides
    MainRouter provideFragmentManager() {
        if(activity.inTwoPaneMode()) {
            return new TwoPaneRouter(activity.getSupportFragmentManager(), activity);
        } else {
            return new MainActivityRouter(activity.getSupportFragmentManager());
        }
    }
}