package com.ikholopov.yamblz.weather.weathermobilization.model.network.dto.places;

/**
 * Created by turist on 28.07.2017.
 */

public class Location {

    public final double lat;
    public final double lng;

    public Location(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
