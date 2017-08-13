package com.ikholopov.yamblz.weather.weathermobilization.model.network;

import com.ikholopov.yamblz.weather.weathermobilization.model.network.dto.places.CityShortInfo;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by turist on 28.07.2017.
 */

public interface PlacesService {
    Single<CityInfo> details(String placeId);
    Single<CityShortInfo[]> autocomplete(String data);
}
