package com.ikholopov.yamblz.weather.weathermobilization.data;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by igor on 7/16/17.
 */

public abstract class WeatherLoader<D> extends AsyncTaskLoader<D> {
    public WeatherLoader(Context context) {
        super(context);
    }

    abstract void setForceLoad();
}
