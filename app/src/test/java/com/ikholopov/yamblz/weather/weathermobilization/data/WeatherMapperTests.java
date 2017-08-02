package com.ikholopov.yamblz.weather.weathermobilization.data;

import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by turist on 24.07.2017.
 */

@RunWith(JUnitParamsRunner.class)
public class WeatherMapperTests {

    @Test
    public void formatTemperatureForCelsius() throws Exception {
        Context context = mock(Context.class);
        WeatherMapper mapper = new WeatherMapper(context, null);

        when(context.getString(R.string.celsius)).thenReturn("c");
        when(context.getString(R.string.format_temperature, 5.0, "c")).thenReturn("cel");

        String cel = mapper.mapTemperature(5.0, true);

        assertThat(cel).isEqualTo("cel");
    }

    @Test
    public void formatTemperatureForFahrenheit() throws Exception {
        Context context = mock(Context.class);
        WeatherMapper mapper = new WeatherMapper(context, null);

        when(context.getString(R.string.farhenheit)).thenReturn("f");
        when(context.getString(R.string.format_temperature, 41.0, "f")).thenReturn("far");

        String far = mapper.mapTemperature(5.0, false);

        assertThat(far).isEqualTo("far");
    }

    static Object[] imageForWeather() {
        return new Object[]{
                new Object[]{201, R.mipmap.thunderstorm},
                new Object[]{301, R.mipmap.rain},
                new Object[]{501, R.mipmap.rain},
                new Object[]{511, R.mipmap.snow},
                new Object[]{520, R.mipmap.rain},
                new Object[]{600, R.mipmap.snow},
                new Object[]{701, R.mipmap.clouds},
                new Object[]{800, R.mipmap.sun},
                new Object[]{801, R.mipmap.mixed},
                new Object[]{802, R.mipmap.mixed},
                new Object[]{803, R.mipmap.clouds},
                new Object[]{1000, -1}
        };
    }

    @Test
    @Parameters(method = "imageForWeather")
    public void getImageIdForWeatherId(int weather, int image) throws Exception {
        WeatherMapper mapper = new WeatherMapper(null, null);

        assertThat(mapper.mapImageId(weather)).isEqualTo(image);
    }
}
