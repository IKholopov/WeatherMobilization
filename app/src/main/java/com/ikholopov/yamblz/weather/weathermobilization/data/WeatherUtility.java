package com.ikholopov.yamblz.weather.weathermobilization.data;

import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.R;

/**
 * Utility for dealing with weather icons and units
 * Created by igor on 7/16/17.
 */

public class WeatherUtility {

    private static final double FARHENHEIT_SCALE = 9.0 / 5.0;
    private static final double FARHENHEIT_BASE = 32.0;

    public static String formatTemperature(Context context, double temperature, boolean isMetric) {

        double temp;
        String grad;
        if ( !isMetric ) {
            temp = FARHENHEIT_SCALE * temperature + FARHENHEIT_BASE;
            grad = context.getString(R.string.farhenheit);
        } else {
            temp = temperature;
            grad = context.getString(R.string.celsius);
        }
        return context.getString(R.string.format_temperature, temp, grad);
    }

    public static int getImageIdForWeatherId(int weatherId) {
        if (weatherId >= 200 && weatherId <= 232) {
            return R.mipmap.thunderstorm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.mipmap.rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.mipmap.rain;
        } else if (weatherId == 511) {
            return R.mipmap.snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.mipmap.rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.mipmap.snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.mipmap.clouds;
        } else if (weatherId == 761 || weatherId == 781) {
            return R.mipmap.thunderstorm;
        } else if (weatherId == 800) {
            return R.mipmap.sun;
        } else if (weatherId == 801) {
            return R.mipmap.mixed;
        } else if (weatherId == 802) {
            return R.mipmap.mixed;
        } else if (weatherId >= 803 && weatherId <= 804) {
            return R.mipmap.clouds;
        }
        return -1;
    }
}
