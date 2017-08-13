package com.ikholopov.yamblz.weather.weathermobilization;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by turist on 13.08.2017.
 */

public class TestSchedulers extends SchedulerProvider {

    public TestScheduler scheduler = new TestScheduler();

    @Override
    public Scheduler main() {
        return scheduler;
    }

    @Override
    public Scheduler computation() {
        return scheduler;
    }

    @Override
    public Scheduler io() {
        return scheduler;
    }
}
