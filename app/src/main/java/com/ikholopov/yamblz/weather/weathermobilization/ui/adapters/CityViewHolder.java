package com.ikholopov.yamblz.weather.weathermobilization.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.CityWeather;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by turist on 11.08.2017.
 */

public class CityViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name) TextView name;

    public interface OnItemClickListener {
        void onClick(int position);
        void onLongClick(int position);
    }

    public CityViewHolder(View itemView, OnItemClickListener clickListener) {
        super(itemView);

        itemView.setOnClickListener(v -> {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                clickListener.onClick(position);
            }
        });

        itemView.setOnLongClickListener(v -> {
            int position = getAdapterPosition();
            if(position == RecyclerView.NO_POSITION) {
                return false;
            }

            clickListener.onLongClick(position);
            return true;
        });

        ButterKnife.bind(this, itemView);
    }

    void onlyCity(String cityName) {
        name.setText(cityName);
    }

    void withWeather(CityWeather weather) {
        name.setText(weather.getLocationName());
    }
}