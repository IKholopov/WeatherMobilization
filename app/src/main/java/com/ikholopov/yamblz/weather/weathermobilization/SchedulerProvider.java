package com.ikholopov.yamblz.weather.weathermobilization;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by turist on 13.08.2017.
 */

public class SchedulerProvider {

    @Inject
    public SchedulerProvider() {
    }

    public Scheduler main() {
        return AndroidSchedulers.mainThread();
    }

    public Scheduler computation() {
        return Schedulers.computation();
    }

    public Scheduler io() {
        return Schedulers.io();
    }
}
