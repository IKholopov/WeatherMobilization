package com.ikholopov.yamblz.weather.weathermobilization.model.network.api;

import com.ikholopov.yamblz.weather.weathermobilization.model.network.dto.places.AutoCompleteResponse;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.dto.places.CityInfoResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by turist on 28.07.2017.
 */

public interface GooglePlacesApi {

    @GET("autocomplete/json")
    Single<AutoCompleteResponse> autoComplete(@Query("input") String data,
                                              @Query("types") String types,
                                              @Query("key") String apiKey,
                                              @Query("language") String language);

    @GET("details/json")
    Single<CityInfoResponse> details(@Query("placeid") String placeid,
                                     @Query("key") String apiKey,
                                     @Query("language") String language);
}
