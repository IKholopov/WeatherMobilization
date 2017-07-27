package com.ikholopov.yamblz.weather.weathermobilization.ui;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ikholopov.yamblz.weather.weathermobilization.R;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @After
    public void tearDown() {
        activityTestRule.getActivity().getSupportLoaderManager().destroyLoader(0);
    }

    @Test
    public void showWeatherOnStartTest() throws Throwable {
        onView(withId(R.id.weather_location)).check(matches(isDisplayed()));
    }

    @Test
    public void navigateToAboutTest() throws Throwable {
        onView(allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)), isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.design_menu_item_text), withText(R.string.nav_drawer_about), isDisplayed()))
                .perform(click());

        onView(withId(R.id.about_message)).check(matches(withText(R.string.about_message)));
    }

    @Test
    public void navigateToSettingsTest() throws Throwable {
        onView(allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)), isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.design_menu_item_text), withText(R.string.nav_drawer_settings), isDisplayed()))
                .perform(click());

        onView(withId(R.id.unitsOption)).check(matches(isDisplayed()));
    }
}