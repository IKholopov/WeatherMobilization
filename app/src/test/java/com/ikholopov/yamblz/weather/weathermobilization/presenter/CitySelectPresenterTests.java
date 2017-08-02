package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import com.ikholopov.yamblz.weather.weathermobilization.data.CityAutoComplete;
import com.ikholopov.yamblz.weather.weathermobilization.data.CityInfo;
import com.ikholopov.yamblz.weather.weathermobilization.data.CurrentWeatherCache;
import com.ikholopov.yamblz.weather.weathermobilization.data.PlacesService;
import com.ikholopov.yamblz.weather.weathermobilization.data.dto.city.CityShortInfo;
import com.ikholopov.yamblz.weather.weathermobilization.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.ui.views.CitySelectView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by turist on 28.07.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CitySelectPresenterTests {

    @Mock PlacesService placesService;
    @Mock PreferencesProvider preferences;
    @Mock CurrentWeatherCache cache;
    @Mock CitySelectView view;

    private CitySelectPresenter presenter;

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });

        presenter = new CitySelectPresenter(placesService, preferences, cache);
    }

    @Test
    public void setAutocompleteToViewTest() throws Exception {
        CityAutoComplete autoComplete = new CityAutoComplete();
        when(view.textChanges()).thenReturn(Observable.<CharSequence>just("city"));
        when(view.selection()).thenReturn(Observable.<CityShortInfo>empty());
        when(placesService.autocomplete("city")).thenReturn(Single.just(autoComplete));

        final TestScheduler testScheduler = new TestScheduler();
        RxJavaPlugins.setComputationSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return testScheduler;
            }
        });

        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return testScheduler;
            }
        });

        presenter.bind(view);
        testScheduler.triggerActions();

        verify(view).accept(autoComplete);
        RxJavaPlugins.reset();
    }

    @Test
    public void allDoneWhenSelectCityTest() throws Exception {
        CityShortInfo cityShortInfo = new CityShortInfo();
        CityInfo cityInfo = new CityInfo("my city", 12.3f, 45.6f);
        cityShortInfo.place_id = "cityid";
        when(view.textChanges()).thenReturn(Observable.<CharSequence>empty());
        when(view.selection()).thenReturn(Observable.just(cityShortInfo));
        when(placesService.details("cityid")).thenReturn(Single.just(cityInfo));

        final TestScheduler testScheduler = new TestScheduler();
        RxJavaPlugins.setComputationSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return testScheduler;
            }
        });

        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return testScheduler;
            }
        });

        presenter.bind(view);
        testScheduler.advanceTimeTo(500, TimeUnit.MILLISECONDS);
        testScheduler.triggerActions();

        verify(preferences).saveCity(cityInfo);
        verify(cache).clear();
        verify(view).onSelected();

        RxJavaPlugins.reset();
    }
}