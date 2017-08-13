package com.ikholopov.yamblz.weather.weathermobilization.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;
import com.ikholopov.yamblz.weather.weathermobilization.di.component.ActivityComponent;
import com.ikholopov.yamblz.weather.weathermobilization.di.module.ActivityModule;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.MainPresenter;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.TwoPaneRouter;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.MainView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

public class MainActivity extends AppCompatActivity
        implements MainView, TwoPaneRouter.RightPanelReplaced {

    @Inject ActivityComponent viewComponent;
    @Inject MainPresenter presenter;

    @Nullable
    @BindView(R.id.right_container) FrameLayout detailsContainer;

    @Nullable
    @BindView(R.id.select_city_hint) TextView selectCityHint;

    public ActivityComponent getViewComponent() {
        if(viewComponent == null) {
            viewComponent = WeatherApplication.get(this).getComponent()
                    .activityComponentBuilder()
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return viewComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getViewComponent().inject(this);
        presenter.bind(this, savedInstanceState != null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbind();
    }

    @Override
    public boolean inTwoPaneMode() {
        return detailsContainer != null;
    }

    @Override
    public void onReplaced() {
        if(selectCityHint != null) {
            selectCityHint.setVisibility(View.INVISIBLE);
        }
    }
}