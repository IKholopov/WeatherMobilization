package com.ikholopov.yamblz.weather.weathermobilization.di.component;

import com.ikholopov.yamblz.weather.weathermobilization.di.PerView;
import com.ikholopov.yamblz.weather.weathermobilization.ui.CitySelectActivity;

import dagger.Component;

/**
 * Provides Activity, Composer for activity and presenter
 */

@PerView
@Component(dependencies = ApplicationComponent.class)
public interface CitySelectComponent {
    void inject(CitySelectActivity activity);
}
