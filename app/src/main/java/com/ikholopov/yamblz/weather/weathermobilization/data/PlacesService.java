package com.ikholopov.yamblz.weather.weathermobilization.data;

import io.reactivex.Single;

/**
 * Created by turist on 28.07.2017.
 */

public interface PlacesService {
    Single<CityInfo> details(String placeId);
    Single<CityAutoComplete> autocomplete(String data);
}
