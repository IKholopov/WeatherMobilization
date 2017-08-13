package com.ikholopov.yamblz.weather.weathermobilization.ui;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ikholopov.yamblz.weather.weathermobilization.ui.PageObjects.CitiesPage;
import com.ikholopov.yamblz.weather.weathermobilization.ui.PageObjects.SettingsPage;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void showCitiesOnStartTest() throws Throwable {
        CitiesPage.obtain().assertOn();
    }

    @Test
    public void navigateToSettingAndBackTest() throws Throwable {
        CitiesPage.obtain().clickSettings();
        SettingsPage.obtain().assertOn();

        SettingsPage.obtain().clickBack();
        CitiesPage.obtain().assertOn();
    }
}