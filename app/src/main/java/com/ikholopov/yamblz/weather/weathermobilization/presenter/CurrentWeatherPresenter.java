package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import com.ikholopov.yamblz.weather.weathermobilization.ui.MainActivity;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.WeatherFragment;

/**
 * Created by igor on 7/16/17.
 */

public interface CurrentWeatherPresenter {
    void bind(WeatherFragment weatherFragment);
    void forceReload();
}
