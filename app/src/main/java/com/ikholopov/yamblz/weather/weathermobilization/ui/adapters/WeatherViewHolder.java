package com.ikholopov.yamblz.weather.weathermobilization.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.Weather;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by turist on 11.08.2017.
 */

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.state_image) ImageView state;
    @BindView(R.id.time) TextView time;
    @BindView(R.id.temperature) TextView temperature;

    public WeatherViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void set(Weather weather) {
        state.setImageResource(weather.weatherImageId);
        time.setText(weather.dateTime);

        Context context = temperature.getContext();
        String unit = context.getString(weather.temperatureUnit);
        String temperatureWithDegree = context
                .getString(R.string.format_temperature, weather.temperature, unit);
        temperature.setText(temperatureWithDegree);
    }
}