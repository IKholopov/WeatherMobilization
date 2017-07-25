package com.ikholopov.yamblz.weather.weathermobilization.data.http;

import android.net.Uri;

import java.util.Map;

/**
 * Created by turist on 25.07.2017.
 */

public class WeatherUriHelper implements UriHelper {

    private static final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    @Override
    public String create(Map<String, String> params) {
        Uri.Builder builder = Uri.parse(FORECAST_BASE_URL).buildUpon();
        for(Map.Entry<String, String> keyValue : params.entrySet()) {
            builder.appendQueryParameter(keyValue.getKey(), keyValue.getValue());
        }

        return builder.build().toString();
    }
}
