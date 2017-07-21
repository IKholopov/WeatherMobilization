package com.ikholopov.yamblz.weather.weathermobilization.di.module;

import com.ikholopov.yamblz.weather.weathermobilization.presenter.CurrentWeatherPresenter;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.CurrentWeatherPresenterImpl;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.UpdateServiceController;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.WeatherServiceController;

import dagger.Module;
import dagger.Provides;

/**
 * Provides presenter
 * Created by igor on 7/20/17.
 */

@Module
public class CurrentWeatherPresenterModule {
    @Provides
    CurrentWeatherPresenter providePresenter() {
        return new CurrentWeatherPresenterImpl();
    }
}
