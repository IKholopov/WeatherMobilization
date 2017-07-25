package com.ikholopov.yamblz.weather.weathermobilization.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonSyntaxException;
import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.data.http.HttpHelper;
import com.ikholopov.yamblz.weather.weathermobilization.data.http.HttpHelperImpl;
import com.ikholopov.yamblz.weather.weathermobilization.data.http.UriHelper;
import com.ikholopov.yamblz.weather.weathermobilization.data.http.WeatherUriHelper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;

/**
 * Handles updating weather and loading it from local file
 * Created by igor on 7/16/17.
 */

public class CurrentWeatherLoader extends WeatherLoader<CurrentWeather> {

    private static final String TAG = "CurrentWeatherLoader";

    private boolean forceNetLoad = false;
    private CurrentWeather currentWeather;
    private CurrentWeatherFileCache weatherCache;
    private HttpHelper httpHelper;
    private UriHelper uriHelper;

    public CurrentWeatherLoader(Context context,
                                CurrentWeatherFileCache weatherCache,
                                HttpHelper httpHelper,
                                UriHelper uriHelper) {
        super(context);
        this.weatherCache = weatherCache;
        this.httpHelper = httpHelper;
        this.uriHelper = uriHelper;
    }


    public CurrentWeatherLoader(Context context) {
        this(context, new CurrentWeatherFileCache(context), new HttpHelperImpl(), new WeatherUriHelper());
    }

    @Override
    public CurrentWeather loadInBackground() {
        if(forceNetLoad) {
            fetchAndSaveCurrentWeatherJson();
        }

        if(currentWeather == null || forceNetLoad) {
            try {
                currentWeather = weatherCache.load();
            } catch (IOException | JsonSyntaxException e) {
                Log.e(TAG, "Failed to read cache file");
                e.printStackTrace();
                currentWeather = null;
            }
        }

        if(currentWeather == null) {
            fetchAndSaveCurrentWeatherJson();
            try {
                currentWeather = weatherCache.load();
            } catch (IOException | JsonSyntaxException e) {
                Log.e(TAG, "Failed to read cache file");
                e.printStackTrace();
                currentWeather = null;
            }
        }

        forceNetLoad = false;
        return currentWeather;
    }

    //Call to update weather data from the net, rather than local file
    @Override
    public void forceNetLoad() {
        forceNetLoad = true;
        this.forceLoad();
    }

    //Must not be called on MainThread
    private void fetchAndSaveCurrentWeatherJson() {
        final String QUERY_PARAM = "q";
        final String MODE_PARAM = "mode";
        final String UNITS_PARAM = "units";
        final String APPID_PARAM = "appid";

        final String mode = "json";
        final String units = "metric";

        HashMap<String, String> params = new HashMap<>();
        params.put(QUERY_PARAM, getContext().getString(R.string.location));
        params.put(MODE_PARAM, mode);
        params.put(UNITS_PARAM, units);
        params.put(APPID_PARAM, getContext().getString(R.string.api_key));
        String urlString = uriHelper.create(params);

        try {
            String jsonStr = httpHelper.get(urlString);

            if(jsonStr != null) {
                weatherCache.save(jsonStr);
            }
        }
        catch (MalformedURLException e) {
            Log.e(TAG, "Invalid url");
        }
        catch (ProtocolException e) {
            Log.e(TAG, "Protocol error");
        }
        catch (IOException e) {
            Log.e(TAG, "Url connection failed");
        }
    }
}
