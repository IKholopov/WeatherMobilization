package com.ikholopov.yamblz.weather.weathermobilization.model.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ikholopov.yamblz.weather.weathermobilization.model.CityForecast;
import com.ikholopov.yamblz.weather.weathermobilization.model.Forecast;

import org.threeten.bp.Instant;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by turist on 05.08.2017.
 */

@Dao
public interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Forecast forecast);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Forecast> forecast);

    @Query("SELECT * FROM forecasts WHERE city_id = :cityId")
    Flowable<List<Forecast>> forCity(long cityId);

    @Query("SELECT * FROM forecasts WHERE city_id = :cityId")
    List<Forecast> blockingForCity(long cityId);

    @Query("DELETE FROM forecasts WHERE city_id = :cityId")
    void deleteForCity(long cityId);

    @Query("SELECT date_time, temperature, pressure, wind_speed, wind_degree, state, description, name " +
           "FROM forecasts, cities WHERE forecasts.city_id = cities.id ORDER BY date_time, name")
    Flowable<CityForecast[]> all();
}