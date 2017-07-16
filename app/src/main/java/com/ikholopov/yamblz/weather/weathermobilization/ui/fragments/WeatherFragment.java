package com.ikholopov.yamblz.weather.weathermobilization.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;
import com.ikholopov.yamblz.weather.weathermobilization.data.WeatherUtility;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.Metric;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.CurrentWeatherPresenter;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.CurrentWeatherPresenterImpl;
import com.ikholopov.yamblz.weather.weathermobilization.ui.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main {@link Fragment} with weather.
 * {@link WeatherFragment#newInstance} fabric-method
 */
public class WeatherFragment extends Fragment implements Named {

    public static final int FragmentNameId = R.string.nav_drawer_weather;

    private String metric;
    private CurrentWeather weather = null;
    private CurrentWeatherPresenter presenter;

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
        metric = getString(PreferencesProvider.getMetricFromPreference(getContext())
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
                PreferencesProvider.getMetricFromPreference(getContext()) == Metric.CELSIUS));

    }

    //Update weather
    public void setWeather(CurrentWeather weather) {
        this.weather = weather;
        if(refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        updateWeatherDisplay();
    }
}
