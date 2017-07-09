package com.ikholopov.yamblz.weather.weathermobilization;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ikholopov.yamblz.weather.weathermobilization.ui.AboutFragment;
import com.ikholopov.yamblz.weather.weathermobilization.ui.Named;
import com.ikholopov.yamblz.weather.weathermobilization.ui.SettingsFragment;
import com.ikholopov.yamblz.weather.weathermobilization.ui.WeatherFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(findViewById(R.id.content_frame) != null) {
            if(savedInstanceState == null) {
                WeatherFragment fragment = WeatherFragment.newInstance();
                fragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment,
                        fragment.getName())
                        .commit();
                updateToolbar(fragment);
            }
        }
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        updateToolbar(current);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        updateToolbar(current);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_weather) {
            if(shouldReplaceFragment(WeatherFragment.mFragmentName)) {
                placeFragment(WeatherFragment.newInstance(), false);
            }
        } else if(id == R.id.nav_settings) {
            if(shouldReplaceFragment(SettingsFragment.mFragmentName)) {
                placeFragment(SettingsFragment.newInstance(), true);
            }
        } else if(id == R.id.nav_about) {
            if(shouldReplaceFragment(AboutFragment.mFragmentName)) {
                placeFragment(AboutFragment.newInstance(), true);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean shouldReplaceFragment(String fragmentName) {
        Named currentFragment = (Named)getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        if(currentFragment == null || !currentFragment.getName().equals(fragmentName)) {
            return true;
        }
        return false;
    }

    private void placeFragment(Fragment fragment, boolean addToStack) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment);
        if(addToStack) {
            transaction = transaction.addToBackStack(null);
        }
        transaction.commit();
        updateToolbar(fragment);
    }

    private void updateToolbar(Fragment current) {
        if(current != null && current instanceof Named) {
            getSupportActionBar().setTitle(((Named)current).getName());
        }
    }
}
