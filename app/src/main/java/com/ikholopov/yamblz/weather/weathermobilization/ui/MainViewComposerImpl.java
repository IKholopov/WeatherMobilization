package com.ikholopov.yamblz.weather.weathermobilization.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.ui.MainViewComposer;
import com.ikholopov.yamblz.weather.weathermobilization.ui.Named;
import com.ikholopov.yamblz.weather.weathermobilization.ui.NavigatableActivity;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.AboutFragment;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.SettingsFragment;
import com.ikholopov.yamblz.weather.weathermobilization.ui.fragments.WeatherFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by igor on 7/15/17.
 */

public class MainViewComposerImpl implements MainViewComposer {

    private NavigatableActivity activity = null;
    private ActionBarDrawerToggle toggle;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @Override
    public void bind(@NonNull NavigatableActivity activity) {
        this.activity = activity;
        ButterKnife.bind(this, activity);
    }

    @Override
    public void setUpView(@NonNull boolean initial) {
        if(activity == null) {
            return;
        }
        activity.setSupportActionBar(toolbar);
        if(activity.findViewById(R.id.content_frame) != null && initial) {
            WeatherFragment fragment = WeatherFragment.newInstance();
            fragment.setArguments(activity.getIntent().getExtras());
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment)
                    .commit();
        }
        Fragment current = activity.getSupportFragmentManager().findFragmentById(R.id.content_frame);
        toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.syncState();
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
        navigationView.setNavigationItemSelectedListener(activity);
        updateToolbar(current);
    }

    @Override
    public void handleNavigation(int navId) {
        if(activity == null) {
            return;
        }
        switch(navId) {
            case R.id.nav_weather:
                if(shouldReplaceFragment(WeatherFragment.FragmentNameId)) {
                    placeFragment(WeatherFragment.newInstance(), false);
                }
                break;
            case R.id.nav_settings:
                if(shouldReplaceFragment(SettingsFragment.FragmentNameId)) {
                    placeFragment(SettingsFragment.newInstance(), true);
                }
                break;
            case R.id.nav_about:
                if(shouldReplaceFragment(AboutFragment.FragmentNameId)) {
                    placeFragment(AboutFragment.newInstance(), true);
                }
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if(activity == null) {
            return;
        }
        Fragment current = activity.getSupportFragmentManager().findFragmentById(R.id.content_frame);
        updateToolbar(current);
    }

    private boolean shouldReplaceFragment(int fragmentNameId) {
        Named currentFragment = (Named)activity.getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        return currentFragment == null ||
                currentFragment.getNameId() != fragmentNameId;
    }

    private void placeFragment(@NonNull Fragment fragment, boolean addToStack) {
        activity.getSupportFragmentManager()
                .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment);
        if(addToStack) {
            transaction = transaction.addToBackStack(null);
        }
        transaction.commit();
        updateToolbar(fragment);
    }

    private void updateToolbar(Fragment current) {
        if(current != null && current instanceof Named) {
            activity.getSupportActionBar().setTitle(
                    activity.getString(((Named)current).getNameId()));
            activity.getSupportFragmentManager().executePendingTransactions();
            int fragmentsInStack = activity.getSupportFragmentManager().getBackStackEntryCount();
            if(fragmentsInStack > 0) {
                toggle.setDrawerIndicatorEnabled(false);
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
            else {
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
                toggle.setDrawerIndicatorEnabled(true);
            }
        }
    }
}
