package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.ForecastFragment;

/**
 * Presenter for main screen
 * Created by igor on 7/16/17.
 */

public interface CurrentWeatherPresenter {
    void bind(ForecastFragment weatherFragment);
    void forceReload();
}
