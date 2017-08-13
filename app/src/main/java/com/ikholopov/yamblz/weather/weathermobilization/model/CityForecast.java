package com.ikholopov.yamblz.weather.weathermobilization.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;

/**
 * Created by turist on 05.08.2017.
 */

public class CityForecast {

    @ColumnInfo(name = "date_time")
    public final long dateTime;

    @ColumnInfo(name = "temperature")
    public final double celsiusTemperature;

    @ColumnInfo(name = "pressure")
    public final double pressure;

    @ColumnInfo(name = "wind_speed")
    public final double windSpeed;

    @ColumnInfo(name = "wind_degree")
    public final double windDegree;

    @Embedded
    public final WeatherState state;

    @ColumnInfo(name = "name")
    public final String cityName;

    public CityForecast(long dateTime, double celsiusTemperature, double pressure, double windSpeed,
                        double windDegree, WeatherState state, String cityName) {
        this.dateTime = dateTime;
        this.celsiusTemperature = celsiusTemperature;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDegree = windDegree;
        this.state = state;
        this.cityName = cityName;
    }
}