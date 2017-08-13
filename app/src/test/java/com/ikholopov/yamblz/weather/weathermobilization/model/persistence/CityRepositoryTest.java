package com.ikholopov.yamblz.weather.weathermobilization.model.persistence;

import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.dao.CityDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Maybe;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by turist on 13.08.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CityRepositoryTest {

    @Mock CityDao dao;
    CityRepository repository;

    @Before
    public void setUp() {
        repository = new CityRepository(dao);
    }

    @Test
    public void insert() throws Exception {
        City city = mock(City.class);

        repository.insert(city);

        dao.insert(city);
    }

    @Test
    public void delete() throws Exception {
        City city = mock(City.class);

        repository.delete(city);

        dao.delete(city);
    }

    @Test
    public void getByPlaceId() throws Exception {
        City city = mock(City.class);
        when(dao.getByPlaceId("id")).thenReturn(Maybe.just(city));

        City res = repository.getByPlaceId("id").blockingGet();

        assertEquals(res, city);
    }
}