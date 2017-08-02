package com.ikholopov.yamblz.weather.weathermobilization.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.CurrentWeatherPresenter;
import com.ikholopov.yamblz.weather.weathermobilization.ui.views.Weather;
import com.ikholopov.yamblz.weather.weathermobilization.ui.MainActivity;
import com.ikholopov.yamblz.weather.weathermobilization.ui.Named;
import com.ikholopov.yamblz.weather.weathermobilization.ui.views.WeatherView;
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * Main {@link Fragment} with weather.
 */
public class WeatherFragment extends KnifeFragment
        implements Named, WeatherView {

    public static final int FRAGMENT_NAME_ID = R.string.nav_drawer_weather;

    @Inject CurrentWeatherPresenter presenter;

    @BindView(R.id.weather_location) TextView weatherLocation;
    @BindView(R.id.weather_message) TextView weatherMessage;
    @BindView(R.id.weather_icon_view) ImageView weatherIcon;
    @BindView(R.id.refresh_layout) SwipeRefreshLayout refreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getViewComponent().inject(this);
        View view = bind(inflater.inflate(R.layout.fragment_weather, container, false));
        presenter.bind(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.refresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unbind();
    }

    @Override
    public int getNameId() {
        return FRAGMENT_NAME_ID;
    }

    private void setWeather(@NonNull Weather weather) {
        weatherIcon.setVisibility(View.VISIBLE);
        weatherIcon.setImageResource(weather.getWeatherImageId());
        weatherLocation.setText(weather.getLocationName());
        weatherMessage.setText(weather.getTemperature());
    }

    @Override
    public void onError() {
        weatherLocation.setText(R.string.no_internet_connection);
        weatherIcon.setVisibility(View.INVISIBLE);
        weatherMessage.setText(R.string.swipe_down_to_refresh);
        stopRefreshing();
    }

    @Override
    public void accept(@NonNull Weather weather) throws Exception {
        setWeather(weather);
        stopRefreshing();
    }

    @Override
    public Observable<Object> refreshes() {
        return RxSwipeRefreshLayout.refreshes(refreshLayout);
    }

    private void stopRefreshing() {
        if(refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }
}