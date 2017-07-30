package com.ikholopov.yamblz.weather.weathermobilization.di.module;

import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.data.GooglePlacesService;
import com.ikholopov.yamblz.weather.weathermobilization.data.PlacesService;
import com.ikholopov.yamblz.weather.weathermobilization.data.http.GooglePlacesApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by turist on 28.07.2017.
 */

@Module
public class GoogleApiModule {

    private static final String API_BASE_URL = "https://maps.googleapis.com/maps/api/place/";

    @Provides
    GooglePlacesApi provideApi() {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GooglePlacesApi.class);
    }

    @Provides
    PlacesService provideService(Context context, GooglePlacesApi api) {
        return new GooglePlacesService(context, api);
    }
}