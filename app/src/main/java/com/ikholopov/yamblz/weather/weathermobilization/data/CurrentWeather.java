package com.ikholopov.yamblz.weather.weathermobilization.data;

import com.ikholopov.yamblz.weather.weathermobilization.data.dto.weather.Clouds;
import com.ikholopov.yamblz.weather.weathermobilization.data.dto.weather.Coordinates;
import com.ikholopov.yamblz.weather.weathermobilization.data.dto.weather.SystemInfo;
import com.ikholopov.yamblz.weather.weathermobilization.data.dto.weather.Weather;
import com.ikholopov.yamblz.weather.weathermobilization.data.dto.weather.WeatherInfo;
import com.ikholopov.yamblz.weather.weathermobilization.data.dto.weather.Wind;

/**
 * POJO for weather provided by OpenWeatherApi
 * Created by igor on 7/16/17.
 */

public class CurrentWeather {

    Coordinates coord;
    Weather[] weather;
    String base;
    WeatherInfo main;
    int visibility;
    Wind wind;
    Clouds clouds;
    int dt;
    SystemInfo sys;
    int id;
    String name;
    int cod;

    public int getId() {
        return id;
    }

    public float getTemp() {
        return main.temp;
    }

    public String getLocationName() {
        return name;
    }

    public int getWeatherId() {
        return weather[0].id;
    }
}