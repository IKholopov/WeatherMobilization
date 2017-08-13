package com.ikholopov.yamblz.weather.weathermobilization.model.preferences;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by turist on 14.08.2017.
 */
public class TemperatureFormatTest {
    @Test
    public void celsiusFormat() throws Exception {
        assertEquals(TemperatureFormat.create(TemperatureFormat.CELSIUS)
                .convert(1.0), 1.0, 0.0005);
    }

    @Test
    public void fahrenheitFormat() throws Exception {
        assertEquals(TemperatureFormat.create(TemperatureFormat.FAHRENHEIT)
                .convert(5.0), 41.0, 0.0005);
    }
}