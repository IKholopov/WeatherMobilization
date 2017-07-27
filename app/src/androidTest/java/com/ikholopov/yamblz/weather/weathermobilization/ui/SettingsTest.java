package com.ikholopov.yamblz.weather.weathermobilization.ui;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.Metric;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class SettingsTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private PreferencesProvider preferences;

    @Before
    public void setUp() {
        preferences = WeatherApplication.get(activityTestRule.getActivity()).getComponent().getPreferencesProvider();

        onView(allOf(withContentDescription("Open navigation drawer"),
                     withParent(withId(R.id.toolbar)), isDisplayed()))
            .perform(click());

        onView(allOf(withId(R.id.design_menu_item_text), withText(R.string.nav_drawer_settings), isDisplayed()))
            .perform(click());
    }

    @After
    public void tearDown() {
        activityTestRule.getActivity().getSupportLoaderManager().destroyLoader(0);
    }

    @Test
    public void changeUnitsOptionTest() {
        Metric old = preferences.getMetricFromPreference();

        onView(allOf(withId(R.id.switchWidget), withParent(withChild(withId(R.id.unitsOption))), isDisplayed())).perform(click());

        Metric new_ = preferences.getMetricFromPreference();

        assertThat(old).isNotEqualTo(new_);
    }

    @Test
    public void changeAutoUpdateOptionTest() {
        boolean old = preferences.getAutoupdateEnabledPreference();

        onView(allOf(withId(R.id.switchWidget), withParent(withParent(withChild(withChild(withText(R.string.background_update))))), isDisplayed())).perform(click());

        boolean new_ = preferences.getAutoupdateEnabledPreference();

        assertThat(old).isNotEqualTo(new_);
    }
}
