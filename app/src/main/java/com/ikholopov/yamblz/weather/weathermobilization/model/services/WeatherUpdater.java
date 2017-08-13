package com.ikholopov.yamblz.weather.weathermobilization.model.services;

import com.ikholopov.yamblz.weather.weathermobilization.model.City;

/**
 * Created by turist on 13.08.2017.
 */

public interface WeatherUpdater {

    void updateWeather();
    void updateWeather(City city);
}
