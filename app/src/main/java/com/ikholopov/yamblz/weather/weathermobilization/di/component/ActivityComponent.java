package com.ikholopov.yamblz.weather.weathermobilization.di.component;

import com.ikholopov.yamblz.weather.weathermobilization.di.module.ActivityModule;
import com.ikholopov.yamblz.weather.weathermobilization.ui.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void injectMainActivity(MainActivity activity);
}
