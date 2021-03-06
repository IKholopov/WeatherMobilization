package com.ikholopov.yamblz.weather.weathermobilization;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeatherLoader;

/**
 * Created by igor on 7/16/17.
 */

public class WeatherUpdateService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    private static final String SERVICE_NAME = "WEATHER_UPDATE_SERVICE";
    private static final int SERVICE_ID = 0;

    public WeatherUpdateService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        CurrentWeatherLoader loader = WeatherApplication.get(getApplicationContext()).getComponent().getWeatherLoader();
        loader.forceNetLoad();
    }

    public static void setServiceEnabled(Context applicationContext, int interval) {
        Intent intent = new Intent(applicationContext, WeatherUpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getService(applicationContext,
                SERVICE_ID, intent, 0);

        AlarmManager manager = (AlarmManager) applicationContext.getSystemService(ALARM_SERVICE);
        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000,
                interval * 60 * 1000, pendingIntent);
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
