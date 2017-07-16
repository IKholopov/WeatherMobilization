package com.ikholopov.yamblz.weather.weathermobilization.data;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.di.component.PerActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by igor on 7/16/17.
 */

public class CurrentWeatherLoader extends WeatherLoader<CurrentWeather> {

    private final String CACHED_FILE_NAME= "CachedCurrentWeather.json";
    private final String TAG = "CurrentWeatherLoader";

    private boolean forceNetLoad;
    private CurrentWeather currentWeather;

    public CurrentWeatherLoader(Context context) {
        super(context);
        forceNetLoad = false;
        currentWeather = null;
    }

    @Override
    public CurrentWeather loadInBackground() {
        if(forceNetLoad) {
            fetchAndSaveCurrentWeatherJson();
        }
        if(currentWeather == null || forceNetLoad) {
            try {
                currentWeather = loadWeatherFromFile();
            } catch (IOException e) {
                Log.e(TAG, "Failed to read cache file");
                e.printStackTrace();
            }
        }
        forceNetLoad = false;
        return currentWeather;
    }

    @Override
    public void forceNetLoad() {
        forceNetLoad = true;
        this.forceLoad();
    }

    //Must not be called on MainThread
    private String fetchAndSaveCurrentWeatherJson() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonStr = null;
        String line;
        final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
        final String QUERY_PARAM = "q";
        final String MODE_PARAM = "mode";
        final String APPID_PARAM = "appid";

        final String mode = "json";

        String urlString = Uri.parse(FORECAST_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, getContext().getString(R.string.location))
                .appendQueryParameter(MODE_PARAM, mode)
                .appendQueryParameter(APPID_PARAM, getContext().getString(R.string.api_key))
                .build().toString();
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setUseCaches(false);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            if(inputStream == null)
                return null;
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while((line = reader.readLine()) != null)
                stringBuffer.append(line);
            if(stringBuffer.length() == 0)
                return null;
            jsonStr = stringBuffer.toString();
            saveToCache(jsonStr);
            return jsonStr;

        } catch (MalformedURLException e) {
            Log.e(TAG, "Invalid url");
            e.printStackTrace();
        } catch (ProtocolException e) {
            Log.e(TAG, "Protocol error");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, "Url connection failed");
            e.printStackTrace();
        }
        return null;
    }

    private CurrentWeather loadWeatherFromFile() throws IOException {
        InputStream file = null;
        try {
            file = new FileInputStream(new File(getContext().getFilesDir(), CACHED_FILE_NAME));

        } catch (FileNotFoundException e) {
            fetchAndSaveCurrentWeatherJson();
        } try {
            file = new FileInputStream(new File(getContext().getFilesDir(), CACHED_FILE_NAME));
            int size = file.available();
            byte[] buffer = new byte[size];
            file.read(buffer);
            String jsonString = new String(buffer, "UTF-8");
            Gson gson = new GsonBuilder().create();
            CurrentWeather weather = gson.fromJson(jsonString, CurrentWeather.class);
            return weather;
        }
        finally {
            if(file != null) {
                file.close();
            }
        }
    }

    private void saveToCache(String jsonString) throws IOException {
        OutputStream file = null;
        try {
            file = new FileOutputStream(new File(getContext().getFilesDir(), CACHED_FILE_NAME));
            file.write(jsonString.getBytes());

        } catch (FileNotFoundException e) {
            Log.e(TAG, "Failed to create cache file");
        } catch (IOException e) {
            Log.e(TAG, "Failed to write to cache file");
            e.printStackTrace();
        } finally {
            if(file != null) {
                file.close();
            }
        }
    }
}
