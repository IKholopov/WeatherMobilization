package com.ikholopov.yamblz.weather;

import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeatherFileCache;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeatherLoader;
import com.ikholopov.yamblz.weather.weathermobilization.data.http.HttpHelper;
import com.ikholopov.yamblz.weather.weathermobilization.data.http.UriHelper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

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
public class WeatherFileCacheTests {

    @Mock Context context;

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        File createdFolder= folder.newFolder("cache");
        when(context.getFilesDir()).thenReturn(createdFolder);
        when(context.getApplicationContext()).thenReturn(context);
    }

    @Test
    public void loadAfterSaveTest() throws Exception {
        CurrentWeatherFileCache currentWeatherFileCache = new CurrentWeatherFileCache(context);

        currentWeatherFileCache.save("{ \"id\" : 42 }");

        CurrentWeather loaded = currentWeatherFileCache.load();

        assertThat(loaded.getId()).isEqualTo(42);
    }
}