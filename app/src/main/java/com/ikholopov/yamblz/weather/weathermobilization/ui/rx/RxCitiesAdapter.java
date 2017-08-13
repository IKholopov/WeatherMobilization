package com.ikholopov.yamblz.weather.weathermobilization.ui.rx;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.ikholopov.yamblz.weather.weathermobilization.ui.adapters.CitiesAdapter;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.CityWeather;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static com.jakewharton.rxbinding2.internal.Preconditions.checkMainThread;
import static com.jakewharton.rxbinding2.internal.Preconditions.checkNotNull;

/**
 * Created by turist on 11.08.2017.
 */

public final class RxCitiesAdapter {

    @CheckResult
    @NonNull
    public static Observable<CityWeather> clicks(@NonNull CitiesAdapter adapter) {
        checkNotNull(adapter, "view == null");
        return new ClicksObservable(adapter);
    }

    @CheckResult
    @NonNull
    public static Observable<CityWeather> longClicks(@NonNull CitiesAdapter adapter) {
        checkNotNull(adapter, "view == null");
        return new LongClicksObservable(adapter);
    }

    private final static class ClicksObservable extends Observable<CityWeather> {
        private final CitiesAdapter adapter;

        ClicksObservable(@NonNull CitiesAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        protected void subscribeActual(Observer<? super CityWeather> observer) {
            if (!checkMainThread(observer)) {
                return;
            }
            ClicksObservable.Listener listener = new ClicksObservable.Listener(adapter, observer);
            observer.onSubscribe(listener);
            adapter.setClickListener(listener);
        }

        static final class Listener extends MainThreadDisposable
                implements CitiesAdapter.OnItemClickListener {
            private final CitiesAdapter adapter;
            private final Observer<? super CityWeather> observer;

            Listener(CitiesAdapter adapter, Observer<? super CityWeather> observer) {
                this.adapter = adapter;
                this.observer = observer;
            }

            @Override
            protected void onDispose() {
                adapter.setClickListener(null);
            }

            @Override
            public void onClick(CityWeather cityWeather) {
                if (!isDisposed()) {
                    observer.onNext(cityWeather);
                }
            }
        }
    }

    private final static class LongClicksObservable extends Observable<CityWeather> {
        private final CitiesAdapter adapter;

        LongClicksObservable(@NonNull CitiesAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        protected void subscribeActual(Observer<? super CityWeather> observer) {
            if (!checkMainThread(observer)) {
                return;
            }
            LongClicksObservable.Listener listener = new LongClicksObservable.Listener(adapter, observer);
            observer.onSubscribe(listener);
            adapter.setLongClickListener(listener);
        }

        static final class Listener extends MainThreadDisposable
                implements CitiesAdapter.OnItemLongClickListener {
            private final CitiesAdapter adapter;
            private final Observer<? super CityWeather> observer;

            Listener(CitiesAdapter adapter, Observer<? super CityWeather> observer) {
                this.adapter = adapter;
                this.observer = observer;
            }

            @Override
            protected void onDispose() {
                adapter.setLongClickListener(null);
            }

            @Override
            public void onLongClick(CityWeather cityWeather) {
                if (!isDisposed()) {
                    observer.onNext(cityWeather);
                }
            }
        }
    }
}