package com.ikholopov.yamblz.weather.weathermobilization.model.network.dto.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by turist on 04.08.2017.
 */

public class ForecastInfo {

    @SerializedName("dt")
    public long utcDatetimeSeconds;

    public Main main;

    @SerializedName("weather")
    public Weather[] weatherStates;

    public Clouds clouds;
    public Wind wind;
}
