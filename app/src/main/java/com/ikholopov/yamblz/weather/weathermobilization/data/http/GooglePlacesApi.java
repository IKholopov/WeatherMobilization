package com.ikholopov.yamblz.weather.weathermobilization.data.http;

import com.ikholopov.yamblz.weather.weathermobilization.data.CityAutoComplete;
import com.ikholopov.yamblz.weather.weathermobilization.data.dto.city.CityInfo;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by turist on 28.07.2017.
 */

public interface GooglePlacesApi {

    @GET("autocomplete/json")
    Single<CityAutoComplete> autoComplete(@Query("input") String data,
                                          @Query("types") String types,
                                          @Query("key") String apiKey,
                                          @Query("language") String language);

    @GET("details/json")
    Single<CityInfo> details(@Query("placeid") String placeid,
                             @Query("key") String apiKey,
                             @Query("language") String language);
}
