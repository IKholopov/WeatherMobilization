package com.ikholopov.yamblz.weather.weathermobilization.data;

import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.data.http.GooglePlacesApi;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by turist on 28.07.2017.
 */

public class GooglePlacesService implements PlacesService {

    private String apiKey;
    private GooglePlacesApi api;

    @Inject
    public GooglePlacesService(Context context, GooglePlacesApi api) {
        this.apiKey = context.getString(R.string.google_api_key);
        this.api = api;
    }

    @Override
    public Single<CityInfo> details(String placeId) {
        return api.details(placeId, apiKey, getLang())
                .map(new Function<com.ikholopov.yamblz.weather.weathermobilization.data.dto.city.CityInfo, CityInfo>() {
                    @Override
                    public CityInfo apply(@NonNull com.ikholopov.yamblz.weather.weathermobilization.data.dto.city.CityInfo cityInfo) throws Exception {
                        return new CityInfo(cityInfo.result.formatted_address, cityInfo.result.geometry.location.lat, cityInfo.result.geometry.location.lng);
                    }
                });
    }

    @Override
    public Single<CityAutoComplete> autocomplete(String data) {
        return api.autoComplete(data, "(cities)", apiKey, getLang());
    }

    private String getLang() {
        return Locale.getDefault().getLanguage();
    }
}
