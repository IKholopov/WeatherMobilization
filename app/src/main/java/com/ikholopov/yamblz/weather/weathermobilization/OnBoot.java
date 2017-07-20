package com.ikholopov.yamblz.weather.weathermobilization;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;

import javax.inject.Inject;

/**
 * Created by igor on 7/18/17.
 */

public class OnBoot extends BroadcastReceiver {
    @Inject
    PreferencesProvider preferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        WeatherApplication.get(context).getComponent().inject(this);
        WeatherUpdateService.setServiceEnabled(context,
                preferences.getAutoupdateEnabledPreference(),
                preferences.getUpdateInterval());
    }
}
