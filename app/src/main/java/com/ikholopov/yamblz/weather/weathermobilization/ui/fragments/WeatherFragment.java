package com.ikholopov.yamblz.weather.weathermobilization.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;
import com.ikholopov.yamblz.weather.weathermobilization.data.WeatherUtility;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.Metric;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.CurrentWeatherPresenter;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.CurrentWeatherPresenterImpl;
import com.ikholopov.yamblz.weather.weathermobilization.ui.MainActivity;
import com.ikholopov.yamblz.weather.weathermobilization.ui.Named;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main {@link Fragment} with weather.
 * {@link WeatherFragment#newInstance} fabric-method
 */
public class WeatherFragment extends Fragment implements Named, ForecastFragment {

    public static final int FragmentNameId = R.string.nav_drawer_weather;

    private String metric;
    private CurrentWeather weather = null;
    @Inject CurrentWeatherPresenter presenter;

    @Inject PreferencesProvider preferences;

    @BindView(R.id.weather_location) TextView weatherLocation;
    @BindView(R.id.weather_message) TextView weatherMessage;
    @BindView(R.id.weather_icon_view) ImageView weatherIcon;
    @BindView(R.id.refresh_layout) SwipeRefreshLayout refreshLayout;

    public WeatherFragment() {
        // Required empty constructor
    }

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //WeatherApplication.get(this.getContext()).getComponent().inject(this);
        ((MainActivity)getActivity()).getViewComponent().inject(this);
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, rootView);
        presenter = new CurrentWeatherPresenterImpl();
        presenter.bind(this);
        weatherMessage.setText(weatherMessage.getText() + " " + metric);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.forceReload();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(refreshLayout.isRefreshing()) {
                            refreshLayout.setRefreshing(false);
                        }
                    }
                }, 3000);
            }
        });
        updateWeatherDisplay();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateWeatherDisplay();
    }

    @Override
    public int getNameId() {
        return FragmentNameId;
    }


    //Update displayed weather
    private void updateWeatherDisplay() {
        metric = getString(preferences.getMetricFromPreference()
                .getStringId());
        if(weather == null) {
            weatherLocation.setText(getString(R.string.no_internet_connection));
            weatherIcon.setVisibility(View.INVISIBLE);
            weatherMessage.setText(getString(R.string.swipe_down_to_refresh));
            return;
        }
        weatherIcon.setVisibility(View.VISIBLE);
        weatherIcon.setImageResource(WeatherUtility.getImageIdForWeatherId(weather.getWeatherId()));
        weatherLocation.setText(weather.getLocationName());
        weatherMessage.setText(WeatherUtility.formatTemperature(getContext(), weather.getTemp(),
                preferences.getMetricFromPreference() == Metric.CELSIUS));

    }

    @Override
    public void setWeather(@Nullable CurrentWeather weather) {
        this.weather = weather;
        if(refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        updateWeatherDisplay();
    }

    @Override
    public FragmentActivity getActivityAttachedTo() {
        return getActivity();
    }
}
