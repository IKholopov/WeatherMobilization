package com.ikholopov.yamblz.weather.weathermobilization.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.ikholopov.yamblz.weather.weathermobilization.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends NavigatableActivity {

    private MainViewComposer composer;

    @BindView(R.id.drawer_layout) DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        composer = new MainViewComposerImpl();
        composer.bind(this);
        composer.setUpView(savedInstanceState == null);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
            composer.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        composer.handleNavigation(id);
        return true;
    }
}
