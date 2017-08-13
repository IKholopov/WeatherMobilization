package com.ikholopov.yamblz.weather.weathermobilization.model.network.dto.places;

import com.google.gson.annotations.SerializedName;

/**
 * Created by turist on 28.07.2017.
 */

public class CityShortInfo {

    @SerializedName("description")
    public final String description;

    @SerializedName("place_id")
    public final String placeId;

    public CityShortInfo(String description, String placeId) {
        this.description = description;
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return description;
    }
}
