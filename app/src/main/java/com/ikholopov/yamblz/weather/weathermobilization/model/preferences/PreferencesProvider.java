package com.ikholopov.yamblz.weather.weathermobilization.model.preferences;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import org.threeten.bp.Duration;

import javax.inject.Inject;

/**
 * Simple SharedPreferences wrapper
 * Created by igor on 7/14/17.
 */

public class PreferencesProvider {

    private static final String TEMPERATURE_FORMAT_KEY = "temperature_format";
    private static final String AUTO_UPDATE_INTERVAL_KEY = "auto_update_interval";

    private final SharedPreferences sharedPreferences;

    @Inject
    public PreferencesProvider(@NonNull SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @TemperatureFormat.Units
    public int getTemperatureFormat() {
        @TemperatureFormat.Units
        int value = sharedPreferences
                .getInt(TEMPERATURE_FORMAT_KEY, TemperatureFormat.CELSIUS);

        return value;
    }

    public void putTemperatureFormat(@TemperatureFormat.Units int unit) {
        sharedPreferences
                .edit()
                .putInt(TEMPERATURE_FORMAT_KEY, unit)
                .apply();
    }

    public Duration getAutoUpdateInterval() {
        long minutes = sharedPreferences.getLong(AUTO_UPDATE_INTERVAL_KEY, 30);
        return Duration.ofMinutes(minutes);
    }

    public void putAutoUpdateInterval(Duration duration) {
        sharedPreferences
                .edit()
                .putLong(AUTO_UPDATE_INTERVAL_KEY, duration.toMinutes())
                .apply();
    }
}