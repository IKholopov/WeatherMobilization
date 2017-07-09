package com.ikholopov.yamblz.weather.weathermobilization.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.Metric;

/**
 * {@link Fragment} для отображения погоды.
 * {@link WeatherFragment#newInstance} метод-фабрика для создания фрагмента.
 */
public class WeatherFragment extends Fragment implements Named {

    public final static String mFragmentName = "Weather";

    private String mMessage;

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

        mMessage = Metric.getMetricFromPreference(getContext()).toString(getContext());
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        TextView textView = (TextView)rootView.findViewById(R.id.weather_message);
        textView.setText(textView.getText() + " " + mMessage);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMessage = Metric.getMetricFromPreference(getContext()).toString(getContext());
    }

    @Override
    public String getName() {
        return mFragmentName;
    }
}
