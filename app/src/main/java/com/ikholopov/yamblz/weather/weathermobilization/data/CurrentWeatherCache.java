package com.ikholopov.yamblz.weather.weathermobilization.data;

import java.io.IOException;

/**
 * Created by turist on 25.07.2017.
 */

public interface CurrentWeatherCache {
    void save(String jsonString) throws IOException;
    CurrentWeather load() throws IOException;
    void clear() throws IOException;
}
