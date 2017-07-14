package com.ikholopov.yamblz.weather.weathermobilization.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;

/**
 * Main {@link Fragment} with weather.
 * {@link WeatherFragment#newInstance} fabric-method
 */
public class WeatherFragment extends Fragment implements Named {

    public static final int FragmentNameId = R.string.nav_drawer_weather;

    private String message;

    public WeatherFragment() {
        // Пустой конструктор для родителя
    }

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        updateMessage();
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        TextView textView = (TextView)rootView.findViewById(R.id.weather_message);
        textView.setText(textView.getText() + " " + message);
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
        message = getString(PreferencesProvider.getMetricFromPreference(getContext())
                .getStringId());
    }
}
