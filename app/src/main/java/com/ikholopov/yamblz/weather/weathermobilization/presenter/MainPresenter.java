package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.MainView;

import javax.inject.Inject;

/**
 * Created by turist on 09.08.2017.
 */

public class MainPresenter extends RxPresenter {

    private final MainRouter router;

    @Inject
    public MainPresenter(MainRouter router) {
        this.router = router;
    }

    public void bind(MainView view, boolean hasState) {
        if(!hasState) {
            router.showCitiesList();
        }
    }
}