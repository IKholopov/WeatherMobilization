package com.ikholopov.yamblz.weather.weathermobilization.data;

import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.data.http.HttpHelper;
import com.ikholopov.yamblz.weather.weathermobilization.data.http.UriHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by turist on 24.07.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class WeatherLoaderTests {

    @Mock Context context;
    @Mock CurrentWeatherFileCache cache;
    @Mock HttpHelper httpHelper;
    @Mock UriHelper uriHelper;

    private static final String urlString = "http://api.openweathermap.org/query123";

    @Before
    public void setUp() throws Exception {
        when(context.getString(anyInt())).thenReturn("str");
        when(context.getApplicationContext()).thenReturn(context);

        when(httpHelper.get(urlString)).thenReturn("json");
        when(uriHelper.create(anyMapOf(String.class, String.class))).thenReturn(urlString);
    }

    @Test
    public void needNetLoadTest() throws Exception {
        CurrentWeather currentWeather = new CurrentWeather();
        when(cache.load()).thenReturn(null, currentWeather);

        CurrentWeatherLoader loader = new CurrentWeatherLoader(context, cache, httpHelper, uriHelper);
        CurrentWeather loaded = loader.loadInBackground();

        assertThat(loaded).isEqualTo(currentWeather);
        verify(httpHelper, only()).get(urlString);
        verify(cache).save("json");
    }

    @Test
    public void loadFromCacheTest() throws Exception {
        CurrentWeather currentWeather = new CurrentWeather();
        when(cache.load()).thenReturn(currentWeather);

        CurrentWeatherLoader loader = new CurrentWeatherLoader(context, cache, httpHelper, uriHelper);
        CurrentWeather loaded = loader.loadInBackground();

        assertThat(loaded).isEqualTo(currentWeather);
        verify(httpHelper, never()).get(urlString);
        verify(cache, never()).save(anyString());
    }
}