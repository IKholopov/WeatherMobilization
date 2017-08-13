package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import com.ikholopov.yamblz.weather.weathermobilization.model.City;

/**
 * Created by turist on 09.08.2017.
 */

public interface MainRouter {
    void showCityWeather(City city);
    void showCitiesList();
    void showSettings();
}