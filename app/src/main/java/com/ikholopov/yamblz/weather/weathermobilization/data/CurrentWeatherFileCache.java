package com.ikholopov.yamblz.weather.weathermobilization.data;

import android.content.Context;
import android.support.annotation.Nullable;
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

import io.reactivex.annotations.NonNull;

/**
 * Created by turist on 25.07.2017.
 */

public class CurrentWeatherFileCache implements CurrentWeatherCache {

    private static final String CACHED_FILE_NAME = "CachedCurrentWeather.json";
    private static final String TAG = "CurrentWeatherFileCache";

    private Context context;
    private CurrentWeather currentWeather;
    private Gson gson;

    public CurrentWeatherFileCache(Context context) {
        this.context = context;
        this.gson = new GsonBuilder().create();
    }

    @Nullable
    @Override
    public CurrentWeather load() {
        if(currentWeather == null) {
            InputStream file = null;
            try {
                file = new FileInputStream(new File(context.getFilesDir(), CACHED_FILE_NAME));
                int size = file.available();
                byte[] buffer = new byte[size];
                if(file.read(buffer) == -1) {
                    return null;
                }

                String jsonString = new String(buffer, "UTF-8");
                currentWeather = gson.fromJson(jsonString, CurrentWeather.class);
            } catch (FileNotFoundException ignored) {
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(file != null) {
                    try {
                        file.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return currentWeather;
    }

    @Override
    public void clear() throws IOException {
        currentWeather = null;
        File file = new File(context.getFilesDir(), CACHED_FILE_NAME);
        if(file.exists()) {
            file.delete();
        }
    }

    @Override
    public void accept(@NonNull CurrentWeather weather) throws Exception {
        currentWeather = weather;

        OutputStream file = null;
        try {
            file = new FileOutputStream(new File(context.getFilesDir(), CACHED_FILE_NAME));
            String json = gson.toJson(weather);
            file.write(json.getBytes());
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
}
