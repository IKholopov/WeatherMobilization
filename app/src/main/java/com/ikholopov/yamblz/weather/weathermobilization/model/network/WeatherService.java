package com.ikholopov.yamblz.weather.weathermobilization.model.network;

import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.model.Forecast;

import io.reactivex.Observable;

/**
 * Created by turist on 28.07.2017.
 */

public interface WeatherService {
    Observable<Forecast> forecasts(City city);
}