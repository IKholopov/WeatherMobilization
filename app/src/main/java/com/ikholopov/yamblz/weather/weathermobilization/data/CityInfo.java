package com.ikholopov.yamblz.weather.weathermobilization.data;

/**
 * Created by turist on 28.07.2017.
 */

public class CityInfo {

    private String name;
    private float lat;
    private float lng;

    public CityInfo(String name, float lat, float lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public float getLng() {
        return lng;
    }

    public float getLat() {
        return lat;
    }

    public String getName() {
        return name;
    }
}
