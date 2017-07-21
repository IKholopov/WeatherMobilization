package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ikholopov.yamblz.weather.weathermobilization.WeatherUpdateService;
import com.ikholopov.yamblz.weather.weathermobilization.di.component.ApplicationComponent;

import javax.inject.Inject;

public class WeatherServiceController implements UpdateServiceController{
    @Inject Context context;

    @Override
    public void bindComponent(@NonNull ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void enableService(int updateInterval) {
        if(context == null) {
            throw new IllegalStateException("WeatherServiceController not binded!");
        }
        WeatherUpdateService.setServiceEnabled(context, true,
                updateInterval);
    }
}
