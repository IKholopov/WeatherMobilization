package com.ikholopov.yamblz.weather.weathermobilization.di.component;

import com.ikholopov.yamblz.weather.weathermobilization.di.PerActivity;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.ActivityModule;
import com.ikholopov.yamblz.weather.weathermobilization.ui.MainActivity;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.CitiesFragment;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.SettingsFragment;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.WeatherFragment;

import dagger.Subcomponent;

/**
 * Provides Activity and Presenter
 */

@PerActivity
@Subcomponent(modules = { ActivityModule.class })
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(WeatherFragment fragment);
    void inject(CitiesFragment fragment);
    void inject(SettingsFragment fragment);

    @Subcomponent.Builder interface Builder {
        Builder activityModule(ActivityModule activityModule);
        ActivityComponent build();
    }
}