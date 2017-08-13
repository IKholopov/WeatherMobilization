package com.ikholopov.yamblz.weather.weathermobilization.model.network.dto.places;

import com.google.gson.annotations.SerializedName;

/**
 * Created by turist on 28.07.2017.
 */

public class CityInfoResult {

    @SerializedName("formatted_address")
    public final String formattedAddress;

    @SerializedName("geometry")
    public final Geometry geometry;

    public CityInfoResult(String formattedAddress, Geometry geometry) {
        this.formattedAddress = formattedAddress;
        this.geometry = geometry;
    }
}