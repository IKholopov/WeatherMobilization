package com.ikholopov.yamblz.weather.weathermobilization.model.persistence.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.model.Forecast;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.dao.CityDao;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.dao.ForecastDao;

/**
 * Created by turist on 06.08.2017.
 */

@Database(entities = {City.class, Forecast.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CityDao cityDao();
    public abstract ForecastDao forecastDao();
}