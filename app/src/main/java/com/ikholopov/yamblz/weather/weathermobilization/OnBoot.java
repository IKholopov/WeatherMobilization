package com.ikholopov.yamblz.weather.weathermobilization;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.UpdateServiceController;

import javax.inject.Inject;

/**
 * Created by igor on 7/18/17.
 */

public class OnBoot extends BroadcastReceiver {

    @Inject
    PreferencesProvider preferences;
    @Inject
    UpdateServiceController serviceController;

    @Override
    public void onReceive(Context context, Intent intent) {
        WeatherApplication.get(context).getComponent().inject(this);

        if(preferences.getAutoupdateEnabledPreference()) {
            serviceController.enableService(preferences.getUpdateInterval());
        } else {
            serviceController.disableService();
        }
    }
}
