package com.ikholopov.yamblz.weather.weathermobilization.ui;

import com.ikholopov.yamblz.weather.weathermobilization.data.CityAutoComplete;
import com.ikholopov.yamblz.weather.weathermobilization.data.dto.city.CityShortInfo;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by turist on 28.07.2017.
 */

public interface CitySelectView extends Consumer<CityAutoComplete> {
    Observable<CharSequence> textChanges();
    Observable<CityShortInfo> selection();
    void onSelected();
    void onError();
}
