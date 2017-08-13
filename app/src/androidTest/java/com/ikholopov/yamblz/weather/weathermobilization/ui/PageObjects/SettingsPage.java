package com.ikholopov.yamblz.weather.weathermobilization.ui.PageObjects;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.TemperatureFormat;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.ikholopov.yamblz.weather.weathermobilization.model.preferences.TemperatureFormat.CELSIUS;

/**
 * Created by turist on 13.08.2017.
 */

public class SettingsPage {

    private static SettingsPage instance;

    public static SettingsPage obtain() {
        if (instance == null)
            instance = new SettingsPage();
        return instance;
    }

    public SettingsPage assertOn() {
        onView(withId(R.id.temperature_unit_button)).check(matches(isDisplayed()));
        return instance;
    }

    public SettingsPage clickBack() {
        onView(withId(R.id.back_button)).perform(click());
        return instance;
    }

    public SettingsPage switchTemperature(@TemperatureFormat.Units int target) {
        onView(withId(target == CELSIUS ? R.id.choice_c : R.id.choice_f)).perform(click());
        return instance;
    }
}
