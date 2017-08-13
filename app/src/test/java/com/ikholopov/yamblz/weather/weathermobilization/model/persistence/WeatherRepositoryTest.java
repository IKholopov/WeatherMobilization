package com.ikholopov.yamblz.weather.weathermobilization.model.persistence;

import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.model.Forecast;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.dao.ForecastDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by turist on 13.08.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class WeatherRepositoryTest {

    @Mock ForecastDao dao;
    WeatherRepository repository;

    @Before
    public void setUp() {
        repository = new WeatherRepository(dao);
    }

    @Test
    public void forCity() throws Exception {
        ArrayList<Forecast> list = new ArrayList<>();
        when(dao.forCity(123)).thenReturn(Flowable.just(list));

        City city = new City(123, "", "", null);
        List<Forecast> forecasts = repository.forCity(city).blockingFirst();

        assertEquals(forecasts, list);
    }

    @Test
    public void deleteForCity() throws Exception {
        City city = new City(123, "", "", null);
        repository.deleteForCity(city);

        verify(dao).deleteForCity(123);
    }
}