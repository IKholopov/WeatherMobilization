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
        Context context = getApplicationContext();
        CurrentWeatherLoader loader = new CurrentWeatherLoader(context);
        loader.forceNetLoad();
    }

    public static void setServiceEnabled(Context applicationContext, boolean enabled, int interval) {
        Intent intent = new Intent(applicationContext, WeatherUpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getService(applicationContext,
                SERVICE_ID, intent, 0);

        AlarmManager manager = (AlarmManager) applicationContext.getSystemService(
                applicationContext.ALARM_SERVICE);
        if(enabled) {
            manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000,
                    interval * 60 * 1000, pendingIntent);
        }
        else {
            manager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }
}
