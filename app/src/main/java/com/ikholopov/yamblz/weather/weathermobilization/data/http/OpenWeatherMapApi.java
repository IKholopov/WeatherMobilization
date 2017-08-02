package com.ikholopov.yamblz.weather.weathermobilization.data.http;

import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by turist on 28.07.2017.
 */

public interface OpenWeatherMapApi {

    @GET("2.5/weather")
    Single<CurrentWeather> weatherByCityId(@Query("appid") String apiKey,
                                           @Query("id") int cityId,
                                           @Query("units") String units,
                                           @Query("lang") String lang);

    @GET("2.5/weather")
    Single<CurrentWeather> weatherByLatLng(@Query("appid") String apiKey,
                                           @Query("lat") double lat,
                                           @Query("lon") double lon,
                                           @Query("units") String units,
                                           @Query("lang") String lang);
}
