package com.ikholopov.yamblz.weather.weathermobilization.ui;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.TemperatureFormat;
import com.ikholopov.yamblz.weather.weathermobilization.ui.PageObjects.CitiesPage;
import com.ikholopov.yamblz.weather.weathermobilization.ui.PageObjects.SettingsPage;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class SettingsTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private PreferencesProvider preferences;

    @Before
    public void setUp() {
        preferences = WeatherApplication.get(activityTestRule.getActivity())
                .getComponent().getPreferencesProvider();

        CitiesPage.obtain().clickSettings();
    }

    @After
    public void tearDown() {
        activityTestRule.getActivity().getSupportLoaderManager().destroyLoader(0);
    }

    @Test
    public void changeUnitsOptionTest() {

        int old = preferences.getTemperatureFormat();
        int target = old == TemperatureFormat.CELSIUS
                ? TemperatureFormat.FAHRENHEIT
                : TemperatureFormat.CELSIUS;

        SettingsPage.obtain().switchTemperature(target);

        assertThat(old).isNotEqualTo(target);
    }
}