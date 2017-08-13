package com.ikholopov.yamblz.weather.weathermobilization.model.persistence;

import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.model.Forecast;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.dao.ForecastDao;

import org.threeten.bp.Instant;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by turist on 13.08.2017.
 */

public class WeatherRepository {

    private final ForecastDao forecastDao;

    @Inject
    public WeatherRepository(ForecastDao forecastDao) {
        this.forecastDao = forecastDao;
    }

    public Flowable<List<Forecast>> forCity(City city) {
        return forecastDao.forCity(city.id);
    }

    public List<Forecast> blockingForCity(City city) {
        return forecastDao.blockingForCity(city.id);
    }

    public void save(List<Forecast> forecasts) {
        forecastDao.insert(forecasts);
    }

    public void deleteForCity(City city) {
        forecastDao.deleteForCity(city.id);
    }
}