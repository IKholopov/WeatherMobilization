package com.ikholopov.yamblz.weather.weathermobilization.data;

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

class Coordinates {
    float lon;
    float lat;
}

class Weather {
    int id;
    String main;
    String description;
    String icon;
}

class WeatherInfo {
    float temp;
    int pressure;
    int humidity;
    float temp_min;
    float temp_max;
}

class Wind {
    float speed;
    int deg;
}

class Clouds {
    int all;
}

class SystemInfo {
    int type;
    int id;
    float message;
    String country;
    int sunrise;
    int sunset;
}
