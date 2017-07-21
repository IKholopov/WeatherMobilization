package com.ikholopov.yamblz.weather.weathermobilization;

import android.app.Application;
import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.di.component.ApplicationComponent;
import com.ikholopov.yamblz.weather.weathermobilization.di.component.DaggerApplicationComponent;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.ApplicationModule;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.PreferencesProviderModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Application
 * Created by igor on 7/16/17.
 */

public class WeatherApplication extends Application {
    protected ApplicationComponent applicationComponent;

    public static WeatherApplication get(Context context) {
        return (WeatherApplication)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .preferencesProviderModule(new PreferencesProviderModule(getApplicationContext()))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
