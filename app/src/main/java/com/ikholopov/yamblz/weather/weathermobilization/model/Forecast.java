package com.ikholopov.yamblz.weather.weathermobilization.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;

import org.threeten.bp.Instant;

/**
 * Created by turist on 04.08.2017.
 */

@Entity(tableName = "forecasts", primaryKeys = {"date_time", "city_id"},
        indices = @Index(value = "city_id"), foreignKeys = @ForeignKey(entity = City.class,
            onDelete = ForeignKey.CASCADE, parentColumns = "id", childColumns = "city_id"))
public class Forecast {

    @ColumnInfo(name = "creating_time")
    public final Instant creatingTime;

    @ColumnInfo(name = "date_time")
    public final Instant dateTime;

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

    @ColumnInfo(name = "city_id")
    public final long cityId;

    public Forecast(Instant creatingTime, Instant dateTime, double celsiusTemperature, double pressure, double windSpeed, double windDegree, WeatherState state, long cityId) {
        this.creatingTime = creatingTime;
        this.dateTime = dateTime;
        this.celsiusTemperature = celsiusTemperature;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDegree = windDegree;
        this.state = state;
        this.cityId = cityId;
    }

    @Ignore
    public Forecast(Instant dateTime, double celsiusTemperature, double pressure, double windSpeed, double windDegree, WeatherState state, City city) {
        this(Instant.now(), dateTime, celsiusTemperature, pressure, windSpeed, windDegree, state, city.id);
    }
}