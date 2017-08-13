package com.ikholopov.yamblz.weather.weathermobilization.presenter.views;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by turist on 08.08.2017.
 */

public interface SettingsView {

    void setTemperatureUnit(boolean isCelsius);
    void setUpdateInterval(String interval);
    void showUpdateIntervalDialog(Consumer<Integer> intervalListener);

    Observable<Boolean> temperatureUnitsSwitches();
    Observable<Object> updateIntervalRequests();
}
