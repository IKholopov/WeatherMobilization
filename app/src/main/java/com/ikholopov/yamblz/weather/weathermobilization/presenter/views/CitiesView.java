package com.ikholopov.yamblz.weather.weathermobilization.presenter.views;

import android.support.annotation.StringRes;

import com.jakewharton.rxbinding2.InitialValueObservable;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by turist on 08.08.2017.
 */

public interface CitiesView extends Consumer<List<CityWeather>> {

    Observable<Object> searchClears();
    Observable<Object> settingsRequests();
    InitialValueObservable<CharSequence> citiesSearches();
    Observable<CityWeather> citiesSelections();
    Observable<CityWeather> citiesOptionSelections();

    Maybe<Boolean> ask(@StringRes int message);

    void setDescription(@StringRes int value);
    void showClearButton(boolean value);
    void clearSearch();

    void onError();
}