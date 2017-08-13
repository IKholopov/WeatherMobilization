package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import com.ikholopov.yamblz.weather.weathermobilization.model.network.PlacesService;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.PreferencesProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by turist on 28.07.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CitySelectPresenterTests {

    @Mock PlacesService placesService;
    @Mock PreferencesProvider preferences;

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });

        //presenter = new CitySelectPresenter(placesService, preferences, cache);
    }

    @Test
    public void setAutocompleteToViewTest() throws Exception {
//        AutoCompleteResponse autoComplete = new AutoCompleteResponse(predictions);
//        when(view.textChanges()).thenReturn(Observable.<CharSequence>just("city"));
//        when(view.selection()).thenReturn(Observable.<CityShortInfo>empty());
//        when(placesService.autocomplete("city")).thenReturn(Single.just(autoComplete));
//
//        final TestScheduler testScheduler = new TestScheduler();
//        RxJavaPlugins.setComputationSchedulerHandler(new Function<Scheduler, Scheduler>() {
//            @Override
//            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
//                return testScheduler;
//            }
//        });
//
//        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
//            @Override
//            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
//                return testScheduler;
//            }
//        });
//
//        presenter.bind(view);
//        testScheduler.triggerActions();
//
//        verify(view).accept(autoComplete);
//        RxJavaPlugins.reset();
    }

    @Test
    public void allDoneWhenSelectCityTest() throws Exception {
//        CityShortInfo cityShortInfo = new CityShortInfo(description, placeId);
//        CityInfo cityInfo = new CityInfo("my city", 12.3f, 45.6f, placeId);
//        cityShortInfo.place_id = "cityid";
//        when(view.textChanges()).thenReturn(Observable.<CharSequence>empty());
//        when(view.selection()).thenReturn(Observable.just(cityShortInfo));
//        when(placesService.details("cityid")).thenReturn(Single.just(cityInfo));
//
//        final TestScheduler testScheduler = new TestScheduler();
//        RxJavaPlugins.setComputationSchedulerHandler(new Function<Scheduler, Scheduler>() {
//            @Override
//            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
//                return testScheduler;
//            }
//        });
//
//        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
//            @Override
//            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
//                return testScheduler;
//            }
//        });
//
//        presenter.bind(view);
//        testScheduler.advanceTimeTo(500, TimeUnit.MILLISECONDS);
//        testScheduler.triggerActions();
//
//        verify(preferences).saveCity(cityInfo);
//        verify(cache).clear();
//        verify(view).onSelected();

        RxJavaPlugins.reset();
    }
}