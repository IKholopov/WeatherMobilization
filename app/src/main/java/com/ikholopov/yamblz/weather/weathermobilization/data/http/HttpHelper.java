package com.ikholopov.yamblz.weather.weathermobilization.data.http;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by turist on 25.07.2017.
 */

public interface HttpHelper {
    String get(String uri) throws IOException;
}
