package com.ikholopov.yamblz.weather.weathermobilization.model.network.dto.places;

/**
 * Created by turist on 28.07.2017.
 */

public class AutoCompleteResponse {

    public final CityShortInfo[] predictions;

    public AutoCompleteResponse(CityShortInfo[] predictions) {
        this.predictions = predictions;
    }
}