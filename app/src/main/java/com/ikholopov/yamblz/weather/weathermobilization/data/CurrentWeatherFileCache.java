package com.ikholopov.yamblz.weather.weathermobilization.data;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by turist on 25.07.2017.
 */

public class CurrentWeatherFileCache implements CurrentWeatherCache {

    private static final String CACHED_FILE_NAME = "CachedCurrentWeather.json";
    private static final String TAG = "CurrentWeatherFileCache";

    private Context context;

    public CurrentWeatherFileCache(Context context) {
        this.context = context;
    }

    @Override
    public void save(String jsonString) throws IOException {
        OutputStream file = null;
        try {
            file = new FileOutputStream(new File(context.getFilesDir(), CACHED_FILE_NAME));
            file.write(jsonString.getBytes());
        }
        catch (FileNotFoundException e) {
            Log.e(TAG, "Failed to create cache file");
        }
        catch (IOException e) {
            Log.e(TAG, "Failed to write to cache file");
        }
        finally {
            if(file != null) {
                file.close();
            }
        }
    }

    @Override
    public CurrentWeather load() throws IOException {
        InputStream file = null;
        try {
            file = new FileInputStream(new File(context.getFilesDir(), CACHED_FILE_NAME));
            int size = file.available();
            byte[] buffer = new byte[size];
            if(file.read(buffer) == -1) {
                return null;
            }

            String jsonString = new String(buffer, "UTF-8");
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(jsonString, CurrentWeather.class);
        }
        catch (FileNotFoundException e) {
            return null;
        }
        finally {
            if(file != null) {
                file.close();
            }
        }
    }
}
