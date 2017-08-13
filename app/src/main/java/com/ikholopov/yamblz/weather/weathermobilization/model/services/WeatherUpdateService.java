package com.ikholopov.yamblz.weather.weathermobilization.model.services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;

import javax.inject.Inject;

/**
 * Created by igor on 7/16/17.
 */

public class WeatherUpdateService extends IntentService {

    private static final String SERVICE_NAME = "WEATHER_UPDATE_SERVICE";
    private static final int SERVICE_ID = 0;

    @Inject WeatherUpdater updater;

    public WeatherUpdateService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        WeatherApplication.get(getApplicationContext())
                .getComponent().inject(this);

        updater.updateWeather();
    }


    public static void setServiceEnabled(Context applicationContext, long intervalMillis) {
        Intent intent = new Intent(applicationContext, WeatherUpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getService(applicationContext,
                SERVICE_ID, intent, 0);

        AlarmManager manager = (AlarmManager) applicationContext.getSystemService(ALARM_SERVICE);
        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000,
                intervalMillis, pendingIntent);
    }

    public static void setServiceDisabled(Context applicationContext) {
        Intent intent = new Intent(applicationContext, WeatherUpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getService(applicationContext,
                SERVICE_ID, intent, 0);

        AlarmManager manager = (AlarmManager) applicationContext.getSystemService(ALARM_SERVICE);
        manager.cancel(pendingIntent);
        pendingIntent.cancel();
    }
}