package com.ikholopov.yamblz.weather.weathermobilization.data;

import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.data.http.OpenWeatherMapApi;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by turist on 28.07.2017.
 */

public class OpenWeatherMapService implements WeatherService {

    private static final String UNITS = "metric";

    private String apiKey;
    private OpenWeatherMapApi api;
    private LanguageProvider languageProvider;

    @Inject
    public OpenWeatherMapService(Context context, OpenWeatherMapApi api,
                                 LanguageProvider languageProvider) {
        this.apiKey = context.getString(R.string.openweathermap_api_key);
        this.api = api;
        this.languageProvider = languageProvider;
    }

    @Override
    public Single<CurrentWeather> currentWeather(double lat, double lng) {
        return api.weatherByLatLng(apiKey, lat, lng, UNITS, languageProvider.getLanguageCode());
    }
}
