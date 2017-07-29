package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.WeatherUpdateService;

import javax.inject.Inject;

public class WeatherServiceController implements UpdateServiceController {

    private Context context;

    @Inject
    public WeatherServiceController(Context context) {
        this.context = context;
    }

    @Override
    public void enableService(int updateInterval) {
        WeatherUpdateService.setServiceEnabled(context, updateInterval);
    }

    @Override
    public void disableService() {
        WeatherUpdateService.setServiceDisabled(context);
    }
}