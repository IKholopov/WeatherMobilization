package com.ikholopov.yamblz.weather.weathermobilization.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.WeatherPresenter;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.Weather;
import com.ikholopov.yamblz.weather.weathermobilization.ui.MainActivity;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.WeatherView;
import com.ikholopov.yamblz.weather.weathermobilization.ui.adapters.WeatherAdapter;
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

/**
 * Main {@link Fragment} with weather.
 */
public class WeatherFragment extends KnifeFragment implements WeatherView {

    private static final String CITY_KEY = "city_id";

    @Inject WeatherPresenter presenter;
    private WeatherAdapter adapter;

    @Nullable
    @BindView(R.id.back_button) View backButton;

    @BindView(R.id.weather_list) RecyclerView weatherList;
    @BindView(R.id.weather_location) TextView weatherLocation;
    @BindView(R.id.weather_message) TextView weatherMessage;
    @BindView(R.id.weather_icon) ImageView weatherIcon;
    @BindView(R.id.refresh_layout) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.forecast) TextView forecastLabel;

    @BindView(R.id.temperature) TextView temperature;
    @BindView(R.id.pressure_label) TextView pressureLabel;
    @BindView(R.id.pressure) TextView pressure;
    @BindView(R.id.updated_label) TextView updatedLabel;
    @BindView(R.id.updated) TextView updated;
    @BindView(R.id.wind_label) TextView windLabel;
    @BindView(R.id.wind_speed) TextView windSpeed;
    @BindView(R.id.wind_icon) ImageView windIcon;

    public static WeatherFragment create(City city) {
        WeatherFragment weatherFragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(CITY_KEY, city);
        weatherFragment.setArguments(bundle);
        return weatherFragment;
    }

    RecyclerView.LayoutManager getLayoutManager() {
        if(backButton != null) {
            return new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        }

        if(getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            return new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        }

        return new GridLayoutManager(getContext(), 2, LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getViewComponent().inject(this);
        View view = bind(inflater.inflate(R.layout.fragment_weather, container, false));

        adapter = new WeatherAdapter();
        weatherList.setAdapter(adapter);
        weatherList.setLayoutManager(getLayoutManager());

        onError();

        City city = getArguments().getParcelable(CITY_KEY);
        presenter.bind(this, city);
        save(presenter);

        if(backButton != null) {
            backButton.setOnClickListener(v -> getActivity().onBackPressed());
        }

        return view;
    }

    private void setWeather(@NonNull Weather weather) {
        weatherIcon.setVisibility(View.VISIBLE);
        weatherIcon.setImageResource(weather.weatherImageId);
        weatherMessage.setText(weather.description);

        forecastLabel.setVisibility(View.VISIBLE);
        pressureLabel.setVisibility(View.VISIBLE);
        updatedLabel.setVisibility(View.VISIBLE);
        windLabel.setVisibility(View.VISIBLE);
        windIcon.setVisibility(View.VISIBLE);

        updated.setText(weather.dateTime);
        pressure.setText(getString(R.string.pressure_format, weather.pressure));
        windSpeed.setText(getString(R.string.speed_format, weather.windSpeed));
        windIcon.setRotation((float)weather.windDegree + 270f);
        temperature.setText(getString(R.string.format_temperature,
                weather.temperature, getString(weather.temperatureUnit)));
    }

    @Override
    public void onError() {
        forecastLabel.setVisibility(View.INVISIBLE);
        pressureLabel.setVisibility(View.INVISIBLE);
        updatedLabel.setVisibility(View.INVISIBLE);
        windLabel.setVisibility(View.INVISIBLE);
        weatherIcon.setVisibility(View.INVISIBLE);
        windIcon.setVisibility(View.INVISIBLE);

        updated.setText("");
        pressure.setText("");
        windSpeed.setText("");
        temperature.setText("");

        weatherMessage.setText(R.string.swipe_down_to_refresh);
        onStopRefreshing();
    }

    @Override
    public Observable<Object> refreshes() {
        return RxSwipeRefreshLayout.refreshes(refreshLayout);
    }

    @Override
    public void setLocation(String name) {
        weatherLocation.setText(name);
    }

    protected void onStopRefreshing() {
        if(refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void accept(@NonNull List<Weather> weathers) throws Exception {
        if(weathers.size() > 0) {
            setWeather(weathers.get(0));
        }

        adapter.accept(weathers);
        onStopRefreshing();
    }
}