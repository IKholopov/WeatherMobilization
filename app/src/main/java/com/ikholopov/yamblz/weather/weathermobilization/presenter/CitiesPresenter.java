package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.util.Log;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.SchedulerProvider;
import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.model.Coordinates;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.PlacesService;
import com.ikholopov.yamblz.weather.weathermobilization.model.persistence.CityRepository;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.CitiesView;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.CityWeather;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by turist on 08.08.2017.
 */

public class CitiesPresenter extends RxPresenter {

    final MainRouter router;
    final PlacesService placesService;
    final CityRepository repository;
    final SchedulerProvider schedulers;

    private CitiesView view;
    private Disposable subscriptionOnSavedCities;

    @Inject
    public CitiesPresenter(MainRouter router, PlacesService placesService,
                           CityRepository repository, SchedulerProvider schedulers) {
        this.router = router;
        this.placesService = placesService;
        this.repository = repository;
        this.schedulers = schedulers;
    }

    public void bind(CitiesView view) {
        this.view = view;

        save(view.settingsRequests()
                .subscribe(o -> router.showSettings()));

        Observable<CharSequence> searches = view.citiesSearches()
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribeOn(schedulers.computation());

        save(searches
                .map(s -> s.length() > 0)
                .observeOn(schedulers.main())
                .subscribe(view::showClearButton));

        save(view.searchClears().subscribe(o -> view.clearSearch()));

        save(searches
                .filter(str -> str.length() > 0)
                .switchMapSingle(this::search)
                .observeOn(schedulers.main())
                .doOnNext(o -> {
                    clearSubscriptionOnSavedCities();
                    view.setDescription(R.string.search_result);
                })
                .subscribe(view, throwable -> {
                    Log.e("autocomplete", throwable.getMessage(), throwable);
                    view.onError();
                }));

        save(searches.filter(str -> str.length() == 0)
                .observeOn(schedulers.main())
                .subscribe(o -> save(subscriptionOnSavedCities = repository.cities()
                        .subscribeOn(schedulers.computation())
                        .flatMapSingle(cities -> Observable.fromArray(cities)
                                .map(CityWeather::new)
                                .toList())
                        .observeOn(schedulers.main())
                        .doOnNext(a -> view.setDescription(R.string.saved_cities))
                        .subscribe(view, throwable -> {
                            Log.e("cities from db", throwable.getMessage(), throwable);
                            view.onError();
                        }))));

        save(view.citiesSelections()
                .observeOn(schedulers.computation())
                .map(CityWeather::getPlaceId)
                .switchMapSingle(placeId ->
                    repository.getByPlaceId(placeId)
                            .switchIfEmpty(placesService.details(placeId)
                                    .map(d -> new City(d.getName(), d.getPlaceId(),
                                            new Coordinates(d.getLat(), d.getLng())))
                            .toMaybe())
                            .toSingle()
                )
                .observeOn(schedulers.main())
                .subscribe(router::showCityWeather, throwable -> {
                    Log.e("city select", throwable.getMessage(), throwable);
                    view.onError();
                }));

        save(view.citiesOptionSelections()
                .observeOn(schedulers.main())
                .subscribe(this::askAction, throwable -> {
                    Log.e("city options", throwable.getMessage(), throwable);
                    view.onError();
                }));
    }

    void clearSubscriptionOnSavedCities() {
        if(subscriptionOnSavedCities != null && !subscriptionOnSavedCities.isDisposed()) {
            subscriptionOnSavedCities.dispose();
        }
    }

    Single<City> getDetails(String placeId) {
        return placesService.details(placeId)
                .map(d -> new City(d.getName(), d.getPlaceId(),
                        new Coordinates(d.getLat(), d.getLng())));
    }

    @Override
    public void unbind() {
        super.unbind();
        view = null;
    }

    void askAction(CityWeather city) {
        if(city.inStorage()) {
            save(view.ask(R.string.delete_city)
                    .observeOn(schedulers.io())
                    .flatMap(o -> repository.getByPlaceId(city.getPlaceId()))
                    .subscribe(repository::delete, throwable -> {
                        Log.e("city delete", throwable.getMessage(), throwable);
                        view.onError();
                    }));
        } else {
            save(view.ask(R.string.save_city)
                    .observeOn(schedulers.io())
                    .flatMapSingle(o -> getDetails(city.getPlaceId()))
                    .subscribe(repository::insert, throwable -> {
                        Log.e("city save", throwable.getMessage(), throwable);
                        view.onError();
                    }));
        }
    }

    SingleSource<List<CityWeather>> search(@NonNull CharSequence charSequence) throws Exception {
        return placesService.autocomplete(charSequence.toString())
                .flatMapObservable(Observable::fromArray)
                .map(cityShortInfo -> new CityWeather(cityShortInfo.placeId, cityShortInfo.description))
                .toList()
                .subscribeOn(schedulers.io());
    }
}