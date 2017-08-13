package com.ikholopov.yamblz.weather.weathermobilization.di.module.application;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.dao.CityDao;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.dao.ForecastDao;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.db.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by turist on 06.08.2017.
 */

@Module
public class RoomModule {

    @Provides
    @Singleton
    AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "weather.sqlite")
                .build();
    }

    @Provides
    CityDao provideCityDao(AppDatabase database) {
        return database.cityDao();
    }

    @Provides
    ForecastDao provideForecastDao(AppDatabase database) {
        return database.forecastDao();
    }
}
