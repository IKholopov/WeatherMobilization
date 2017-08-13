package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.SchedulerProvider;
import com.ikholopov.yamblz.weather.weathermobilization.TestSchedulers;
import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.model.Coordinates;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.CityInfo;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.PlacesService;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.dto.places.CityShortInfo;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.CityRepository;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.CitiesView;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.CityWeather;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by turist on 28.07.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CitiesPresenterTests {

    @Mock MainRouter router;
    @Mock PlacesService placesService;
    @Mock CityRepository repository;
    @Mock SchedulerProvider schedulers;
    @Mock CitiesView view;

    CitiesPresenter presenter;
    TestSchedulers testSchedulers = new TestSchedulers();

    @Before
    public void setUp() {
        when(view.citiesSearches()).thenReturn(Observable.empty());
        when(view.citiesSelections()).thenReturn(Observable.empty());
        when(view.settingsRequests()).thenReturn(Observable.empty());
        when(view.searchClears()).thenReturn(Observable.empty());
        when(view.citiesOptionSelections()).thenReturn(Observable.empty());

        presenter = new CitiesPresenter(router, placesService, repository, testSchedulers);
    }

    @Test
    public void searchReactionTest() throws Exception {
        when(view.citiesSearches()).thenReturn(Observable.just("city"));
        when(placesService.autocomplete("city")).thenReturn(Single.just(new CityShortInfo[0]));

        presenter.bind(view);

        testSchedulers.scheduler.advanceTimeTo(500, TimeUnit.MILLISECONDS);
        testSchedulers.scheduler.triggerActions();

        verify(view).accept(anyListOf(CityWeather.class));
        verify(view).showClearButton(true);
        verify(view).setDescription(R.string.search_result);
    }

    @Test
    public void clearSearchReactionTest() throws Exception {
        when(view.searchClears()).thenReturn(Observable.just(new Object()));

        presenter.bind(view);

        verify(view).clearSearch();
    }

    @Test
    public void searchClearedReactionTest() throws Exception {
        when(view.citiesSearches()).thenReturn(Observable.just("as", ""));
        when(repository.cities()).thenReturn(Flowable.just(new City[]{ new City("as", "id", new Coordinates(12.0, 13.0)) }));

        presenter.bind(view);

        testSchedulers.scheduler.advanceTimeTo(500, TimeUnit.MILLISECONDS);
        testSchedulers.scheduler.triggerActions();

        verify(view).accept(anyListOf(CityWeather.class));
        verify(view).showClearButton(false);
        verify(view).setDescription(R.string.saved_cities);
    }

    @Test
    public void showSettingsTest() throws Exception {
        when(view.settingsRequests()).thenReturn(Observable.just(new Object()));

        presenter.bind(view);

        verify(router).showSettings();
    }

    @Test
    public void citySelectionTest() throws Exception {
        City city = new City("n", "id", new Coordinates(1.0, 2.0));
        when(view.citiesSelections()).thenReturn(Observable.just(new CityWeather(city)));
        when(repository.getByPlaceId("id")).thenReturn(Maybe.just(city));
        when(placesService.details("id")).thenReturn(Single.just(new CityInfo("n", 1.0, 2.0, "id")));

        presenter.bind(view);
        testSchedulers.scheduler.triggerActions();

        verify(router).showCityWeather(city);
    }

    @Test
    public void citySelectionNoStorageTest() throws Exception {
        City city = new City("n", "id", new Coordinates(1.0, 2.0));
        when(view.citiesSelections()).thenReturn(Observable.just(new CityWeather(city)));
        when(repository.getByPlaceId("id")).thenReturn(Maybe.empty());
        when(placesService.details("id")).thenReturn(Single.just(new CityInfo("n", 1.0, 2.0, "id")));

        presenter.bind(view);
        testSchedulers.scheduler.triggerActions();

        verify(router).showCityWeather(isNotNull(City.class));
    }

    @Test
    public void cityOptionDeleteTest() throws Exception {
        CityWeather cityWeather = mock(CityWeather.class);
        City city = mock(City.class);

        when(cityWeather.inStorage()).thenReturn(true);
        when(view.ask(R.string.delete_city)).thenReturn(Maybe.just(true));
        when(view.citiesOptionSelections()).thenReturn(Observable.just(cityWeather));
        when(repository.getByPlaceId(any())).thenReturn(Maybe.just(city));

        presenter.bind(view);
        testSchedulers.scheduler.triggerActions();

        verify(repository).delete(city);
    }
}