package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.support.v4.app.FragmentManager;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.SettingsFragment;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.WeatherFragment;

/**
 * Created by turist on 10.08.2017.
 */

public class TwoPaneRouter extends MainActivityRouter {

    public interface RightPanelReplaced {
        void onReplaced();
    }

    private RightPanelReplaced rightPanelReplaced;

    public TwoPaneRouter(FragmentManager manager, RightPanelReplaced rightPanelReplaced) {
        super(manager);
        this.rightPanelReplaced = rightPanelReplaced;
    }

    @Override
    public void showCityWeather(City city) {
        final String tag = ONE_CITY_TAG + city.id;
        if(needShow(tag)) {
            replaceFragment(WeatherFragment.create(city), R.id.right_container, tag);
            rightPanelReplaced.onReplaced();
        }
    }

    @Override
    public void showSettings() {
        if(needShow(SETTINGS_TAG)) {
            placeBackFragment(new SettingsFragment(), R.id.root_container, SETTINGS_TAG);
        }
    }
}
