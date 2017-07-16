package com.ikholopov.yamblz.weather.weathermobilization.di.component;

import android.app.Application;

import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.ApplicationModule;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.MainComposerModule;
import com.ikholopov.yamblz.weather.weathermobilization.ui.MainViewComposer;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by igor on 7/16/17.
 */

@Singleton
@Component(modules = { ApplicationModule.class, MainComposerModule.class } )
public interface ApplicationComponent {
    void inject(WeatherApplication application);
    void inject(MainViewComposer mainViewComposer);

    Application getApplication();
    MainViewComposer getMainViewComposer();
}
