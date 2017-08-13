package com.ikholopov.yamblz.weather.weathermobilization.ui.fragments;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.CitiesPresenter;
import com.ikholopov.yamblz.weather.weathermobilization.ui.MainActivity;
import com.ikholopov.yamblz.weather.weathermobilization.ui.adapters.CitiesAdapter;
import com.ikholopov.yamblz.weather.weathermobilization.ui.rx.RxCitiesAdapter;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.CitiesView;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.CityWeather;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.subjects.MaybeSubject;

/**
 * Main {@link Fragment} with cities.
 */
public class CitiesFragment extends KnifeFragment implements CitiesView {

    @Inject CitiesPresenter presenter;
    CitiesAdapter citiesAdapter;

    @BindView(R.id.search_edit) EditText searchEdit;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.cities_list) RecyclerView citiesList;
    @BindView(R.id.settings_button) FloatingActionButton settingsButton;
    @BindView(R.id.clear_button) ImageView clearButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getViewComponent().inject(this);
        View view = bind(inflater.inflate(R.layout.fragment_cities, container, false));

        clearButton.setScaleX(0.0f);
        clearButton.setScaleY(0.0f);

        citiesAdapter = new CitiesAdapter();
        citiesList.setAdapter(citiesAdapter);
        citiesList.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter.bind(this);
        save(presenter);

        return view;
    }

    @Override
    public void onError() {
        Snackbar.make(citiesList, R.string.error_has_been, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Observable<Object> searchClears() {
        return RxView.clicks(clearButton);
    }

    @Override
    public Observable<Object> settingsRequests() {
        return RxView.clicks(settingsButton);
    }

    @Override
    public InitialValueObservable<CharSequence> citiesSearches() {
        return RxTextView.textChanges(searchEdit);
    }

    @Override
    public Observable<CityWeather> citiesSelections() {
        return RxCitiesAdapter.clicks(citiesAdapter);
    }

    @Override
    public Observable<CityWeather> citiesOptionSelections() {
        return RxCitiesAdapter.longClicks(citiesAdapter);
    }

    @Override
    public Maybe<Boolean> ask(@StringRes int message) {
        final MaybeSubject<Boolean> subject = MaybeSubject.create();

        new AlertDialog.Builder(getContext())
                .setMessage(message)
                .setPositiveButton(R.string.yes, (dialog, which) -> subject.onSuccess(true))
                .setNegativeButton(R.string.no, (dialog, which) -> {})
                .create()
                .show();

        return subject;
    }

    @Override
    public void setDescription(@StringRes int value) {
        description.setText(value);
    }

    @Override
    public void showClearButton(boolean value) {
        float scale = value ? 1.0f : 0.0f;
        clearButton.animate()
                .scaleY(scale).scaleX(scale)
                .start();
    }

    @Override
    public void clearSearch() {
        searchEdit.setText("");
    }

    @Override
    public void accept(@NonNull List<CityWeather> cityWeathers) throws Exception {
        citiesAdapter.accept(cityWeathers);
    }
}