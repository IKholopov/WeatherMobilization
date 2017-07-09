package com.ikholopov.yamblz.weather.weathermobilization.preferences;

import android.content.Context;
import android.support.v7.preference.PreferenceManager;

import com.ikholopov.yamblz.weather.weathermobilization.R;

/**
 * Enum для работы с метриками температуры
 */

public enum Metric {
    FAHRENHEIT, CELSIUS;

    public static Metric getMetricFromPreference(Context context){
        if(PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(context.getString(R.string.prefernce_key_metrics), true)) {
            return CELSIUS;
        }
        return FAHRENHEIT;
    }

    public String toString(Context context) {
        if(this == Metric.FAHRENHEIT) {
            return context.getString(R.string.farhenheit);
        } else {
            return context.getString(R.string.celsius);
        }
    }
}
