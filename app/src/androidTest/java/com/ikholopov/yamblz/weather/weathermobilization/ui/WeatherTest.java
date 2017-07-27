package com.ikholopov.yamblz.weather.weathermobilization.ui;


import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeather;
import com.ikholopov.yamblz.weather.weathermobilization.data.WeatherUtility;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.Metric;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.LoaderNetController;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class WeatherTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    static class CurrentWeatherMock extends CurrentWeather {

        @Override
        public float getTemp() {
            return 123.45f;
        }

        @Override
        public int getWeatherId() {
            return 200;
        }
    }

    @Test
    public void setWeatherWhenControllerLoadedTest() throws Throwable {
        final Context context = activityTestRule.getActivity();
        final LoaderNetController controller = WeatherApplication.get(context).getComponent().getWeatherNetController();
        PreferencesProvider preferences = WeatherApplication.get(context).getComponent().getPreferencesProvider();

        final CurrentWeather weather = new CurrentWeatherMock();

        activityTestRule.runOnUiThread(new Runnable() {
            public void run() {
                controller.onLoadFinished(null, weather);
            }
        });

        String temperature = WeatherUtility.formatTemperature(context, 123.45f, preferences.getMetricFromPreference() == Metric.CELSIUS);

        onView(withId(R.id.weather_message)).check(matches(withText(temperature)));
    }
}
