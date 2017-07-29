package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeatherLoader;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.ForecastFragment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by turist on 26.07.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherNetControllerTest {

    @Mock CurrentWeatherLoader loader;
    @Mock ForecastFragment fragment;

    @Test
    public void forceNetLoadTest() throws Exception {
        WeatherNetController controller = new WeatherNetController(loader);

        controller.forceNetLoad();

        verify(loader).forceNetLoad();
    }

    @Test
    public void startLoadingWhenBindTest() throws Exception {
        WeatherNetController controller = new WeatherNetController(loader);
        when(fragment.initLoader(0, null, controller)).thenReturn(loader);

        controller.bindForecastFragment(fragment);

        verify(loader).forceLoad();
    }

    @Test
    public void setWeatherWhenLoadedTest() throws Exception {
        WeatherNetController controller = new WeatherNetController(loader);
        when(fragment.initLoader(0, null, controller)).thenReturn(loader);
        controller.bindForecastFragment(fragment);

        CurrentWeather weather = new CurrentWeather();
        controller.onLoadFinished(loader, weather);

        verify(fragment).setWeather(weather);
    }
}