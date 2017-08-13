package com.ikholopov.yamblz.weather.weathermobilization.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.Weather;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by turist on 11.08.2017.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder>
        implements Consumer<List<Weather>> {

    private List<Weather> list = new ArrayList<>();

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        Weather weather = list.get(position);
        holder.set(weather);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void accept(@NonNull List<Weather> weather) throws Exception {
        list.clear();
        list.addAll(weather);
        notifyDataSetChanged();
    }
}