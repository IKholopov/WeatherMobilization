package com.ikholopov.yamblz.weather.weathermobilization.ui.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.MenuItem;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.WeatherUpdateService;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.ui.Named;

/**
 * {@link Named} {@link PreferenceFragmentCompat} with app preferences.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements Named,
        SharedPreferences.OnSharedPreferenceChangeListener {

    public static final int FragmentNameId = R.string.nav_drawer_settings;

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
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
        if(key.equals(getString(R.string.preference_key_autoupdate))){
            WeatherUpdateService.setServiceEnabled(getContext(),
                    PreferencesProvider.getAutoupdateEnabledPreference(getContext()));
        }
    }
}
