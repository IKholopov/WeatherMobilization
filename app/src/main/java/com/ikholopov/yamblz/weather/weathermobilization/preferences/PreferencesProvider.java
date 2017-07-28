package com.ikholopov.yamblz.weather.weathermobilization.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.preference.PreferenceManager;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.data.CityInfo;

/**
 * Simple SharedPreferences wrapper
 * Created by igor on 7/14/17.
 */

public class PreferencesProvider {

    private Context context;

    public PreferencesProvider(@NonNull Context context) {
        this.context = context;
    }

    public Metric getMetricFromPreference(){
        if(PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(context.getString(R.string.preference_key_metrics), true)) {
            return Metric.CELSIUS;
        }
        return Metric.FAHRENHEIT;
    }

    public boolean getAutoupdateEnabledPreference() {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(context.getString(R.string.preference_key_autoupdate), true);
    }

    public int getUpdateInterval() {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(context.getString(R.string.preference_key_update_interval),
                        context.getResources().getInteger(R.integer.default_update_interval));
    }

    public String getCityName() {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.preference_key_city_name),
                        context.getString(R.string.default_city_name));
    }

    public float getCityLat() {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getFloat(context.getString(R.string.preference_key_city_lat),
                        Float.parseFloat(context.getString(R.string.default_city_lat)));
    }

    public float getCityLng() {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getFloat(context.getString(R.string.preference_key_city_lng),
                        Float.parseFloat(context.getString(R.string.default_city_lng)));
    }

    public void saveCity(CityInfo city) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(context.getString(R.string.preference_key_city_name), city.getName());
        editor.putFloat(context.getString(R.string.preference_key_city_lat), city.getLat());
        editor.putFloat(context.getString(R.string.preference_key_city_lng), city.getLng());
        editor.apply();
    }
}