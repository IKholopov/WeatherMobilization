package com.ikholopov.yamblz.weather.weathermobilization.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by turist on 04.08.2017.
 */

public class WeatherState {

    public static final int SUNNY = 0;
    public static final int CLOUDY = 1;
    public static final int DRIZZLE = 2;
    public static final int HAZE = 3;
    public static final int MOSTLY_CLOUDY = 4;
    public static final int SLIGHT_DRIZZLE = 5;
    public static final int SNOW = 6;
    public static final int THUNDERSTORMS = 7;

    /** @hide */
    @IntDef({SUNNY, CLOUDY, DRIZZLE, HAZE, MOSTLY_CLOUDY, SLIGHT_DRIZZLE, SNOW, THUNDERSTORMS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface States {}

    @WeatherState.States
    public final int state;

    public final String description;

    public WeatherState(int state, String description) {
        this.state = state;
        this.description = description;
    }
}