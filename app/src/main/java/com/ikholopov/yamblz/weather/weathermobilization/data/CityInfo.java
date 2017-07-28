package com.ikholopov.yamblz.weather.weathermobilization.data;

/**
 * Created by turist on 28.07.2017.
 */

public class CityInfo {

    private String name;
    private double lat;
    private double lng;

    public CityInfo(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public String getName() {
        return name;
    }
}
