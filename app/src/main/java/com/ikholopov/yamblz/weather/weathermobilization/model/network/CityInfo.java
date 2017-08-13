package com.ikholopov.yamblz.weather.weathermobilization.model.network;

/**
 * Created by turist on 28.07.2017.
 */

public class CityInfo {

    private final String name;
    private final double lat;
    private final double lng;
    private final String placeId;

    public CityInfo(String name, double lat, double lng, String placeId) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.placeId = placeId;
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

    public String getPlaceId() {
        return placeId;
    }
}