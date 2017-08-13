package com.ikholopov.yamblz.weather.weathermobilization.di;

import javax.inject.Qualifier;

/**
 * Created by turist on 08.08.2017.
 */

@Qualifier
public @interface StringQualifier {

    int UNKNOWN = 0;
    int GOOGLE_API_KEY = 1;
    int OPEN_WEATHER_MAP_API_KEY = 2;

    int stringType() default UNKNOWN;
}