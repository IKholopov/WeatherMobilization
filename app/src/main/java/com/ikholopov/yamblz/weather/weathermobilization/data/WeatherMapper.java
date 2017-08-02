package com.ikholopov.yamblz.weather.weathermobilization.data;

import android.content.Context;
import android.support.annotation.DrawableRes;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.Metric;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.ui.views.Weather;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Utility for dealing with weather icons and units
 * Created by igor on 7/16/17.
 */

public class WeatherMapper implements Function<CurrentWeather, Weather> {

    private static final double FAHRENHEIT_SCALE = 9.0 / 5.0;
    private static final double FAHRENHEIT_BASE = 32.0;

    private final Context context;
    private final PreferencesProvider preferences;

    @Inject
    public WeatherMapper(Context context, PreferencesProvider preferences) {
        this.context = context;
        this.preferences = preferences;
    }

    public String mapTemperature(double temperature, boolean isMetric) {
        double temp;
        String grad;
        if ( !isMetric ) {
            temp = FAHRENHEIT_SCALE * temperature + FAHRENHEIT_BASE;
            grad = context.getString(R.string.farhenheit);
        } else {
            temp = temperature;
            grad = context.getString(R.string.celsius);
        }
        return context.getString(R.string.format_temperature, temp, grad);
    }

    @DrawableRes
    public int mapImageId(int weatherId) {
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

    @Override
    public Weather apply(@NonNull CurrentWeather weather) throws Exception {
        String temperature = mapTemperature(weather.getTemp(), preferences.getMetricFromPreference() == Metric.CELSIUS);
        return new Weather(temperature, preferences.getCityName(), mapImageId(weather.getWeatherId()));
    }
}
