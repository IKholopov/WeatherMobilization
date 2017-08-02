package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.support.annotation.CallSuper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Base presenter, containing utility functional for work with {@link io.reactivex} framework
 *
 * Save subscriptions on {@link io.reactivex.Observable} and unsubscribe its when unbinded
 *
 * Created by turist on 02.08.2017.
 */
public class RxPresenter {

    private CompositeDisposable disposables = new CompositeDisposable();

    /**
     * Any subscription must be saved with this method
     */
    protected void save(Disposable subscription) {
        this.disposables.add(subscription);
    }

    @CallSuper
    public void unbind() {
        disposables.clear();
    }
}