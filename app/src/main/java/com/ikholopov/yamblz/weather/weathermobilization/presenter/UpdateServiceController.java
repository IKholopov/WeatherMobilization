package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.support.annotation.NonNull;

import com.ikholopov.yamblz.weather.weathermobilization.di.component.ApplicationComponent;

/**
 * Created by igor on 7/21/17.
 */

public interface UpdateServiceController {
    void bindComponent(@NonNull ApplicationComponent component);
    void enableService(int updateInterval);
}
