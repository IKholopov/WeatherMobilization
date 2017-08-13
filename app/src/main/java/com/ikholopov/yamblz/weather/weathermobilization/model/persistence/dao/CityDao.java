package com.ikholopov.yamblz.weather.weathermobilization.model.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ikholopov.yamblz.weather.weathermobilization.model.City;

import java.util.Collection;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created by turist on 05.08.2017.
 */

@Dao
public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(City city);

    @Query("SELECT * FROM cities WHERE place_id = :placeId")
    Maybe<City> getByPlaceId(String placeId);

    @Delete
    void delete(City city);

    @Query("SELECT * FROM cities")
    Flowable<City[]> all();

    @Query("SELECT * FROM cities")
    City[] blockingAll();
}