package com.ikholopov.yamblz.weather.weathermobilization.model.services;

import android.content.Context;

import org.threeten.bp.Duration;

import javax.inject.Inject;

public class WeatherUpdateServiceController implements UpdateServiceController {

    private final Context context;

    @Inject
    public WeatherUpdateServiceController(Context context) {
        this.context = context;
    }

    @Override
    public void enableService(Duration updateInterval) {
        WeatherUpdateService.setServiceEnabled(context, updateInterval.toMillis());
    }

    @Override
    public void disableService() {
        WeatherUpdateService.setServiceDisabled(context);
    }
}