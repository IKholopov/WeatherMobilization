package com.ikholopov.yamblz.weather.weathermobilization.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.CityWeather;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by turist on 11.08.2017.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CityViewHolder>
        implements Consumer<List<CityWeather>>, CityViewHolder.OnItemClickListener {

    public interface OnItemClickListener {
        void onClick(CityWeather cityWeather);
    }

    public interface OnItemLongClickListener {
        void onLongClick(CityWeather cityWeather);
    }

    private List<CityWeather> list = new ArrayList<>();
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        CityWeather cityWeather = list.get(position);
        if(cityWeather.isOnlyCity()) {
            holder.onlyCity(cityWeather.getLocationName());
        } else {
            holder.withWeather(cityWeather);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void accept(@NonNull List<CityWeather> cityWeather) throws Exception {
        list.clear();
        list.addAll(cityWeather);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(int position) {
        if(clickListener != null) {
            clickListener.onClick(list.get(position));
        }
    }

    @Override
    public void onLongClick(int position) {
        if(longClickListener != null) {
            longClickListener.onLongClick(list.get(position));
        }
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
}