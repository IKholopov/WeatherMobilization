package com.ikholopov.yamblz.weather.weathermobilization.di.module.application;

import com.ikholopov.yamblz.weather.weathermobilization.BuildConfig;
import com.ikholopov.yamblz.weather.weathermobilization.di.StringQualifier;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.api.ForecastMapperProvider;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.api.OpenWeatherMapApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ikholopov.yamblz.weather.weathermobilization.di.StringQualifier.OPEN_WEATHER_MAP_API_KEY;

/**
 * Created by turist on 28.07.2017.
 */

@Module
@Singleton
public class OpenWeatherMapApiModule {

    private static final String API_BASE_URL = "http://api.openweathermap.org/data/";

    @Provides
    @Singleton
    OpenWeatherMapApi provideApi() {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OpenWeatherMapApi.class);
    }

    @Provides
    @Singleton
    @StringQualifier(stringType = OPEN_WEATHER_MAP_API_KEY)
    String provideApiKey() {
        return BuildConfig.OPENWEATHERMAP_API_KEY;
    }

    @Provides
    @Singleton
    ForecastMapperProvider forecastMapperProvider() {
        return new ForecastMapperProvider();
    }
}