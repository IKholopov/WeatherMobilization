package com.ikholopov.yamblz.weather.weathermobilization.di.module;

import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.CurrentWeatherPresenter;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.CurrentWeatherPresenterImpl;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.LoaderNetController;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.UpdateServiceController;

import dagger.Module;
import dagger.Provides;

/**
 * Provides presenter
 * Created by igor on 7/20/17.
 */

@Module
public class CurrentWeatherPresenterModule {
    @Provides
    CurrentWeatherPresenter providePresenter(PreferencesProvider preferences,
                                             UpdateServiceController serviceController,
                                             LoaderNetController weatherNetController) {
        return new CurrentWeatherPresenterImpl(preferences, serviceController, weatherNetController);
    }
}
