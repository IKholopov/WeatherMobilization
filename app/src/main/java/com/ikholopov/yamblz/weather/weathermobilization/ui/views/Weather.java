package com.ikholopov.yamblz.weather.weathermobilization.ui.views;

import android.support.annotation.DrawableRes;

/**
 * Created by turist on 02.08.2017.
 */

public class Weather {

    @DrawableRes
    private int weatherImageId;
    private String temperature;
    private String locationName;

    public Weather(String temperature, String locationName, @DrawableRes int weatherImageId) {
        this.weatherImageId = weatherImageId;
        this.temperature = temperature;
        this.locationName = locationName;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getLocationName() {
        return locationName;
    }

    @DrawableRes
    public int getWeatherImageId() {
        return weatherImageId;
    }
}