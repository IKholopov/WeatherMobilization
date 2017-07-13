package com.ikholopov.yamblz.weather.weathermobilization.ui;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.ikholopov.yamblz.weather.weathermobilization.R;

/**
 * {@link Named} {@link PreferenceFragmentCompat} с настройками приложения.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements Named {

    public static final int FragmentNameId = R.string.nav_drawer_settings;

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public int getNameId() {
        return FragmentNameId;
    }
}
