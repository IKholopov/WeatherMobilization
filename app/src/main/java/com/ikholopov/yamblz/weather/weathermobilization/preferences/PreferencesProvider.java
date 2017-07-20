package com.ikholopov.yamblz.weather.weathermobilization.preferences;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.preference.PreferenceManager;

import com.ikholopov.yamblz.weather.weathermobilization.R;

import javax.inject.Inject;

/**
 * Simple SharedPreferences wrapper
 * Created by igor on 7/14/17.
 */

public class PreferencesProvider {

    Context context;

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
}
