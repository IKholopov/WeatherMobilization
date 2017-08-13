package com.ikholopov.yamblz.weather.weathermobilization.model.preferences;

import android.support.annotation.IntDef;
import android.support.annotation.StringRes;

import com.ikholopov.yamblz.weather.weathermobilization.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class TemperatureFormat {

    public static final int CELSIUS = 0;
    public static final int FAHRENHEIT = 1;

    @IntDef({CELSIUS, FAHRENHEIT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Units {}

    @StringRes
    public abstract int unitStringId();

    public abstract double convert(double celsiusDegree);

    public static TemperatureFormat create(@Units int unit) {
        return unit == CELSIUS ? new CelsiusFormat() : new FahrenheitFormat();
    }

    private static class CelsiusFormat extends TemperatureFormat {

        @Override
        @StringRes
        public int unitStringId() {
            return R.string.celsius;
        }

        @Override
        public double convert(double celsiusDegree) {
            return celsiusDegree;
        }
    }

    private static class FahrenheitFormat extends TemperatureFormat {

        private static final double FAHRENHEIT_SCALE = 9.0 / 5.0;
        private static final double FAHRENHEIT_BASE = 32.0;

        @Override
        @StringRes
        public int unitStringId() {
            return R.string.fahrenheit;
        }

        @Override
        public double convert(double celsiusDegree) {
            return FAHRENHEIT_SCALE * celsiusDegree + FAHRENHEIT_BASE;
        }
    }
}