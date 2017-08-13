package com.ikholopov.yamblz.weather.weathermobilization.di.module.application;

import com.ikholopov.yamblz.weather.weathermobilization.BuildConfig;
import com.ikholopov.yamblz.weather.weathermobilization.di.StringQualifier;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.api.GooglePlacesApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ikholopov.yamblz.weather.weathermobilization.di.StringQualifier.GOOGLE_API_KEY;

/**
 * Created by turist on 28.07.2017.
 */

@Module
@Singleton
public class GoogleApiModule {

    private static final String API_BASE_URL = "https://maps.googleapis.com/maps/api/place/";

    @Provides
    @Singleton
    GooglePlacesApi provideApi() {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GooglePlacesApi.class);
    }

    @Provides
    @Singleton
    @StringQualifier(stringType = GOOGLE_API_KEY)
    String provideApiKey() {
        return BuildConfig.GOOGLE_API_KEY;
    }
}