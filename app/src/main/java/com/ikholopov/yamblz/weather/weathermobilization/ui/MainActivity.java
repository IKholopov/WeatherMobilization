package com.ikholopov.yamblz.weather.weathermobilization.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;
import com.ikholopov.yamblz.weather.weathermobilization.di.component.ViewComponent;
import com.ikholopov.yamblz.weather.weathermobilization.di.component.DaggerViewComponent;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.MainComposerModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends NavigatableActivity {

    @Inject
    MainViewComposer composer;

    private ViewComponent viewComponent;

    @BindView(R.id.drawer_layout) DrawerLayout drawer;

    public ViewComponent getViewComponent() {
        if(viewComponent == null) {
            viewComponent = DaggerViewComponent.builder()
                    .applicationComponent(WeatherApplication.get(this).getComponent())
                    .mainComposerModule(new MainComposerModule())
                    .build();
        }
        return viewComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        getViewComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
