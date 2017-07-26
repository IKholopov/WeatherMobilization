package com.ikholopov.yamblz.weather;

import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.CurrentWeatherPresenterImpl;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.LoaderNetController;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.UpdateServiceController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by turist on 24.07.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class WeatherPresenterTests {

    @Mock PreferencesProvider preferences;
    @Mock UpdateServiceController serviceController;
    @Mock LoaderNetController weatherNetController;

    @Test
    public void callLoadingForceTest() throws Exception {
        CurrentWeatherPresenterImpl presenter = new CurrentWeatherPresenterImpl(preferences, serviceController, weatherNetController);

        presenter.forceReload();

        verify(weatherNetController).forceNetLoad();
    }

    @Test
    public void startUpdatingWhenBindTest() throws Exception {
        when(preferences.getAutoupdateEnabledPreference()).thenReturn(true);
        when(preferences.getUpdateInterval()).thenReturn(2);

        CurrentWeatherPresenterImpl presenter = new CurrentWeatherPresenterImpl(preferences, serviceController, weatherNetController);

        presenter.bind(null);

        verify(serviceController).enableService(2);
    }

    @Test
    public void notStartUpdatingWhenBindTest() throws Exception {
        when(preferences.getAutoupdateEnabledPreference()).thenReturn(false);

        CurrentWeatherPresenterImpl presenter = new CurrentWeatherPresenterImpl(preferences, serviceController, weatherNetController);

        presenter.bind(null);

        verify(serviceController, never()).enableService(anyInt());
    }
}