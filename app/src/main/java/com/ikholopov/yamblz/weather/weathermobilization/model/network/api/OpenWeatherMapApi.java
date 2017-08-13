package com.ikholopov.yamblz.weather.weathermobilization.model.network.api;

import com.ikholopov.yamblz.weather.weathermobilization.model.network.dto.weather.ForecastResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by turist on 28.07.2017.
 */

public interface OpenWeatherMapApi {

    @GET("2.5/forecast")
    Single<ForecastResponse> forecast(@Query("appid") String apiKey,
                                      @Query("lat") double lat,
                                      @Query("lon") double lng,
                                      @Query("units") String units,
                                      @Query("lang") String lang);
}