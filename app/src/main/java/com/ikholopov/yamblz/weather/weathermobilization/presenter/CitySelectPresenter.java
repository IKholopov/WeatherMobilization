package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.util.Log;

import com.ikholopov.yamblz.weather.weathermobilization.data.CityAutoComplete;
import com.ikholopov.yamblz.weather.weathermobilization.data.CityInfo;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeatherCache;
import com.ikholopov.yamblz.weather.weathermobilization.data.PlacesService;
import com.ikholopov.yamblz.weather.weathermobilization.data.dto.city.CityShortInfo;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.ui.views.CitySelectView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.MaybeSource;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by turist on 28.07.2017.
 */

public class CitySelectPresenter {

    private CompositeDisposable disposables = new CompositeDisposable();
    private PlacesService service;
    private PreferencesProvider preferencesProvider;
    private CurrentWeatherCache cache;

    @Inject
    public CitySelectPresenter(PlacesService service, PreferencesProvider preferencesProvider,
                               CurrentWeatherCache cache) {
        this.service = service;
        this.preferencesProvider = preferencesProvider;
        this.cache = cache;
    }

    public void bind(@NonNull final CitySelectView view) {
        disposables.add(
            view.textChanges()
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(@NonNull CharSequence charSequence) throws Exception {
                        return charSequence.length() > 0;
                    }
                })
                .switchMap(new Function<CharSequence, ObservableSource<CityAutoComplete>>() {
                    @Override
                    public ObservableSource<CityAutoComplete> apply(@NonNull CharSequence charSequence) throws Exception {
                        return service.autocomplete(charSequence.toString()).toObservable().subscribeOn(Schedulers.io());
                    }
                }, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            Log.d("autocomplete", throwable.getMessage(), throwable);
                            view.onError();
                        }
                })
        );

        disposables.add(
            view.selection()
                .firstElement()
                .flatMap(new Function<CityShortInfo, MaybeSource<CityInfo>>() {
                    @Override
                    public MaybeSource<CityInfo> apply(@NonNull CityShortInfo cityShortInfo) throws Exception {
                        return service.details(cityShortInfo.place_id).subscribeOn(Schedulers.io()).toMaybe();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CityInfo>() {
                    @Override
                    public void accept(@NonNull CityInfo cityInfo) throws Exception {
                        preferencesProvider.saveCity(cityInfo);
                        cache.clear();
                        view.onSelected();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("details", throwable.getMessage(), throwable);
                        view.onError();
                    }
                })
        );
    }

    public void unbind() {
        disposables.clear();
    }
}