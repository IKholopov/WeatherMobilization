package com.ikholopov.yamblz.weather.weathermobilization.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;
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

    @BindView(R.id.weather_message) TextView mainView;
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
        mainView.setText(mainView.getText() + " " + metric);
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
        updateMessage();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateMessage();
    }

    @Override
    public int getNameId() {
        return FragmentNameId;
    }

    private void updateMessage() {
        metric = getString(PreferencesProvider.getMetricFromPreference(getContext())
                .getStringId());
        mainView.setText(weather == null ? (mainView.getText() + " " + metric)
                : String.format("%s", weather.getTemp()));
    }

    public void setWeather(CurrentWeather weather) {
        this.weather = weather;
        if(refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        updateMessage();
    }
}
