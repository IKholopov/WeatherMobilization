package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.CitiesFragment;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.SettingsFragment;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.WeatherFragment;

/**
 * Created by turist on 09.08.2017.
 */

public class MainActivityRouter implements MainRouter {

    static final String ONE_CITY_TAG = "city";
    private static final String CITIES_LIST_TAG = "cities";
    static final String SETTINGS_TAG = "settings";

    private final FragmentManager manager;

    public MainActivityRouter(FragmentManager manager) {
        this.manager = manager;
    }

    @Override
    public void showCityWeather(City city) {
        final String tag = ONE_CITY_TAG + city.placeId;
        if(needShow(tag)) {
            placeBackFragment(WeatherFragment.create(city), R.id.container, tag);
        }
    }

    @Override
    public void showCitiesList() {
        if(needShow(CITIES_LIST_TAG)) {
            replaceFragment(new CitiesFragment(), R.id.container, CITIES_LIST_TAG);
        }
    }

    @Override
    public void showSettings() {
        if(needShow(SETTINGS_TAG)) {
            placeBackFragment(new SettingsFragment(), R.id.container, SETTINGS_TAG);
        }
    }

    boolean needShow(String tag) {
        return manager.findFragmentByTag(tag) == null;
    }

    void replaceFragment(Fragment fragment, @IdRes int containerId, String tag) {
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        manager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out,
                        R.anim.slide_in_pop, R.anim.slide_out_pop)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(containerId, fragment, tag)
                .commit();
    }

    void placeBackFragment(Fragment fragment, @IdRes int containerId, String tag) {
        manager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out,
                        R.anim.slide_in_pop, R.anim.slide_out_pop)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(containerId, fragment, tag)
                .addToBackStack(null)
                .commit();
    }
}