package com.ikholopov.yamblz.weather.weathermobilization;

import org.junit.Test;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.*;

/**
 * Created by turist on 14.08.2017.
 */
public class SchedulerProviderTest {

    SchedulerProvider provider = new SchedulerProvider();

    @Test
    public void main() throws Exception {
        TestScheduler testScheduler = new TestScheduler();
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> testScheduler);
        assertEquals(provider.main(), testScheduler);
        RxAndroidPlugins.reset();
    }

    @Test
    public void computation() throws Exception {
        TestScheduler testScheduler = new TestScheduler();
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> testScheduler);
        assertEquals(provider.computation(), testScheduler);
        RxJavaPlugins.reset();
    }

    @Test
    public void io() throws Exception {
        TestScheduler testScheduler = new TestScheduler();
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> testScheduler);
        assertEquals(provider.io(), testScheduler);
        RxJavaPlugins.reset();
    }
}