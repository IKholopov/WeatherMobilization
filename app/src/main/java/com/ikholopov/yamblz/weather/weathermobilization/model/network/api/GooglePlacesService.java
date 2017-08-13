package com.ikholopov.yamblz.weather.weathermobilization.model.network.api;

import com.ikholopov.yamblz.weather.weathermobilization.di.StringQualifier;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.LanguageProvider;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.CityInfo;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.PlacesService;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.dto.places.CityShortInfo;

import javax.inject.Inject;

import io.reactivex.Single;

import static com.ikholopov.yamblz.weather.weathermobilization.di.StringQualifier.GOOGLE_API_KEY;

/**
 * Created by turist on 28.07.2017.
 */

public class GooglePlacesService implements PlacesService {

    private final String apiKey;
    private final GooglePlacesApi api;
    private final LanguageProvider languageProvider;

    @Inject
    public GooglePlacesService(@StringQualifier(stringType = GOOGLE_API_KEY) String apiKey,
                               GooglePlacesApi api,
                               LanguageProvider languageProvider) {
        this.apiKey = apiKey;
        this.api = api;
        this.languageProvider = languageProvider;
    }

    @Override
    public Single<CityInfo> details(String placeId) {
        return api.details(placeId, apiKey, languageProvider.getLanguageCode())
                .map(cityInfo -> new CityInfo(
                        cityInfo.result.formattedAddress,
                        cityInfo.result.geometry.location.lat,
                        cityInfo.result.geometry.location.lng,
                        placeId));
    }

    @Override
    public Single<CityShortInfo[]> autocomplete(String data) {
        return api.autoComplete(data, "(cities)", apiKey, languageProvider.getLanguageCode())
                .map(response -> response.predictions);
    }
}