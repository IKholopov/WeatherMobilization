package com.ikholopov.yamblz.weather.weathermobilization.data;

import io.reactivex.Single;

/**
 * Created by turist on 28.07.2017.
 */

public interface WeatherService {
    Single<CurrentWeather> currentWeather(double lat, double lng);
}
