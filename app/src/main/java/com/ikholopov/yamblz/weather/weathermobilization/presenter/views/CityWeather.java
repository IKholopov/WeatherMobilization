package com.ikholopov.yamblz.weather.weathermobilization.presenter.views;

import android.support.annotation.DrawableRes;

import com.ikholopov.yamblz.weather.weathermobilization.model.City;

/**
 * Created by turist on 02.08.2017.
 */

public class CityWeather {

    private final long cityId;
    private final String placeId;

    @DrawableRes
    private final int weatherImageId;
    private final String temperature;
    private final String locationName;

    public CityWeather(String placeId, String locationName) {
        this.placeId = placeId;
        this.weatherImageId = 0;
        this.temperature = null;
        this.locationName = locationName;
        this.cityId = 0;
    }

    public CityWeather(City city) {
        this(city, null, 0);
    }

    public CityWeather(City city, String temperature, @DrawableRes int weatherImageId) {
        this.cityId = city.id;
        this.placeId = city.placeId;
        this.locationName = city.name;
        this.weatherImageId = weatherImageId;
        this.temperature = temperature;
    }

    public boolean isOnlyCity() {
        return temperature == null;
    }

    public boolean inStorage() {
        return cityId > 0;
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

    public String getPlaceId() {
        return placeId;
    }

    public long getCityId() {
        return cityId;
    }
}