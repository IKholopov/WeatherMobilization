package com.ikholopov.yamblz.weather.weathermobilization.di.module;

import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.data.LanguageProvider;
import com.ikholopov.yamblz.weather.weathermobilization.data.OpenWeatherMapService;
import com.ikholopov.yamblz.weather.weathermobilization.data.WeatherService;
import com.ikholopov.yamblz.weather.weathermobilization.data.http.OpenWeatherMapApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by turist on 28.07.2017.
 */

@Module
public class OpenWeatherMapApiModule {

    private static final String API_BASE_URL = "http://api.openweathermap.org/data/";

    @Provides
    OpenWeatherMapApi provideApi() {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherMapApi.class);
    }

    @Provides
    WeatherService provideService(Context context, OpenWeatherMapApi api, LanguageProvider languageProvider) {
        return new OpenWeatherMapService(context, api, languageProvider);
    }
}