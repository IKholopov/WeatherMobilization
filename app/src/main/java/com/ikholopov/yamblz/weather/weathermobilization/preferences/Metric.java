package com.ikholopov.yamblz.weather.weathermobilization.preferences;

import com.ikholopov.yamblz.weather.weathermobilization.R;

/**
 * Enum for temperature metrics
 */

public enum Metric {
    FAHRENHEIT, CELSIUS;

    public int getStringId() {
        if(this == Metric.FAHRENHEIT) {
            return R.string.farhenheit;
        } else {
            return R.string.celsius;
        }
    }
}
