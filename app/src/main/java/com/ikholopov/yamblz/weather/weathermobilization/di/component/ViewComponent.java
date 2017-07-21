package com.ikholopov.yamblz.weather.weathermobilization.di.component;

import com.ikholopov.yamblz.weather.weathermobilization.di.PerView;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.ActivityModule;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.CurrentWeatherPresenterModule;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.MainComposerModule;
import com.ikholopov.yamblz.weather.weathermobilization.ui.MainActivity;
import com.ikholopov.yamblz.weather.weathermobilization.ui.MainViewComposer;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.WeatherFragment;

import dagger.Component;

/**
 * Provides Activity, Composer for activity and presenter
 */

@PerView
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, MainComposerModule.class,
                CurrentWeatherPresenterModule.class })
public interface ViewComponent {
    void inject(MainActivity activity);
    void inject(MainViewComposer mainViewComposer);
    void inject(WeatherFragment fragment);
}
