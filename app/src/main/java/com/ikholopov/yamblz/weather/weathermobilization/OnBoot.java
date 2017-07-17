package com.ikholopov.yamblz.weather.weathermobilization;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;

/**
 * Created by igor on 7/18/17.
 */

public class OnBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        WeatherUpdateService.setServiceEnabled(context,
                PreferencesProvider.getAutoupdateEnabledPreference(context),
                PreferencesProvider.getUpdateInterval(context));
    }
}
