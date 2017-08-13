package com.ikholopov.yamblz.weather.weathermobilization.ui.PageObjects;

import com.ikholopov.yamblz.weather.weathermobilization.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by turist on 13.08.2017.
 */

public class CitiesPage {

    private static CitiesPage instance;

    public static CitiesPage obtain() {
        if (instance == null)
            instance = new CitiesPage();
        return instance;
    }

    public CitiesPage assertOn() {
        onView(withId(R.id.search_edit))
                .check(matches(isDisplayed()));
        onView(withId(R.id.cities_list))
                .check(matches(isDisplayed()));
        return instance;
    }

    public CitiesPage clickSettings() {
        onView(withId(R.id.settings_button)).perform(click());
        return instance;
    }
}
