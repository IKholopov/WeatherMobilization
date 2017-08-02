package com.ikholopov.yamblz.weather.weathermobilization.data;

import android.content.Context;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by turist on 24.07.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class WeatherFileCacheTests {

    @Mock Context context;

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        File createdFolder = folder.newFolder("cache");
        when(context.getFilesDir()).thenReturn(createdFolder);
        when(context.getApplicationContext()).thenReturn(context);
    }

    @Test
    public void loadAfterSaveTest() throws Exception {
        CurrentWeatherFileCache currentWeatherFileCache = new CurrentWeatherFileCache(context);

        CurrentWeather weather = new CurrentWeather();
        weather.id = 42;

        currentWeatherFileCache.accept(weather);

        CurrentWeather loaded = currentWeatherFileCache.load();

        assertThat(loaded).isNotNull();
        assertThat(loaded.id).isEqualTo(42);
    }

    @Test
    public void loadNullAfterClearTest() throws Exception {
        CurrentWeatherFileCache currentWeatherFileCache = new CurrentWeatherFileCache(context);

        CurrentWeather weather = new CurrentWeather();
        currentWeatherFileCache.accept(weather);
        currentWeatherFileCache.clear();

        CurrentWeather loaded = currentWeatherFileCache.load();

        assertThat(loaded).isNull();
    }
}