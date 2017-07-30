package com.ikholopov.yamblz.weather.weathermobilization.data.dto.city;

/**
 * Created by turist on 28.07.2017.
 */

public class CityShortInfo {
    public String description;
    public String place_id;

    @Override
    public String toString() {
        return description;
    }
}
