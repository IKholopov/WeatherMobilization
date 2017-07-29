package com.ikholopov.yamblz.weather.weathermobilization.ui.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.UpdateServiceController;
import com.ikholopov.yamblz.weather.weathermobilization.ui.Named;

import javax.inject.Inject;

/**
 * {@link Named} {@link PreferenceFragmentCompat} with app preferences.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements Named,
        SharedPreferences.OnSharedPreferenceChangeListener {

    public static final int FragmentNameId = R.string.nav_drawer_settings;

    @Inject
    PreferencesProvider preferences;

    @Inject
    UpdateServiceController serviceController;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        WeatherApplication.get(this.getContext()).getComponent().inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onResume() {
        super.onResume();
        PreferenceManager preferenceManager = getPreferenceManager();
        preferenceManager.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        PreferenceManager preferenceManager = getPreferenceManager();
        preferenceManager.getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public int getNameId() {
        return FragmentNameId;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getString(R.string.preference_key_autoupdate))
        || key.equals(getString(R.string.preference_key_update_interval))){
            if(preferences.getAutoupdateEnabledPreference()) {
                serviceController.enableService(preferences.getUpdateInterval());
            } else {
                serviceController.disableService();
            }
        }
    }
}
