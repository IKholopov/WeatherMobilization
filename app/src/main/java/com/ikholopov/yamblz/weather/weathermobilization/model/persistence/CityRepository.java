package com.ikholopov.yamblz.weather.weathermobilization.model.persistence;

import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.dao.CityDao;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created by turist on 13.08.2017.
 */

public class CityRepository {

    private final CityDao cityDao;

    @Inject
    public CityRepository(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public void insert(City city) {
        cityDao.insert(city);
    }

    public void delete(City city) {
        cityDao.delete(city);
    }

    public Maybe<City> getByPlaceId(String placeId) {
        return cityDao.getByPlaceId(placeId);
    }

    public Flowable<City[]> cities(){
        return cityDao.all();
    }

    public City[] blockingAll() {
        return cityDao.blockingAll();
    }
}