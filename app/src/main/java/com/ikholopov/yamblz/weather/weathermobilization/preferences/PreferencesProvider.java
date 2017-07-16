package com.ikholopov.yamblz.weather.weathermobilization.preferences;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.preference.PreferenceManager;

import com.ikholopov.yamblz.weather.weathermobilization.R;

/**
 * Simple SharedPreferences wrapper
 * Created by igor on 7/14/17.
 */

public class PreferencesProvider {
    public static Metric getMetricFromPreference(@NonNull Context context){
        if(PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(context.getString(R.string.preference_key_metrics), true)) {
            return Metric.CELSIUS;
        }
        return Metric.FAHRENHEIT;
    }

    public static boolean getAutoupdateEnabledPreference(@NonNull Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(context.getString(R.string.preference_key_autoupdate), true);
    }
}
