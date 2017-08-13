package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.TemperatureFormat;
import com.ikholopov.yamblz.weather.weathermobilization.model.services.UpdateServiceController;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.SettingsView;
import com.ikholopov.yamblz.weather.weathermobilization.ui.UpdateIntervalFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.threeten.bp.Duration;

import io.reactivex.Observable;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by turist on 14.08.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class SettingsPresenterTest {

    @Mock PreferencesProvider preferences;
    @Mock UpdateIntervalFormat format;
    @Mock UpdateServiceController updateService;
    @Mock SettingsView view;

    SettingsPresenter presenter;

    @Before
    public void setUp() {
        when(view.temperatureUnitsSwitches()).thenReturn(Observable.empty());
        when(view.updateIntervalRequests()).thenReturn(Observable.empty());
        presenter = new SettingsPresenter(preferences, format, updateService);
    }

    @Test
    public void changeTemperatureUnit() throws Exception {
        when(view.temperatureUnitsSwitches()).thenReturn(Observable.just(true, false));

        presenter.bind(view);

        verify(preferences).putTemperatureFormat(TemperatureFormat.CELSIUS);
        verify(preferences).putTemperatureFormat(TemperatureFormat.FAHRENHEIT);
    }

    @Test
    public void showDialogForUpdateInterval() throws Exception {
        when(view.updateIntervalRequests()).thenReturn(Observable.just(0));

        presenter.bind(view);

        verify(view).showUpdateIntervalDialog(anyObject());
    }

    @Test
    public void changeUpdateInterval() throws Exception {
        Duration duration = Duration.ofSeconds(12);
        when(format.getInterval(12)).thenReturn(duration);
        when(format.getString(12)).thenReturn("12");

        presenter.bind(view);
        presenter.saveInterval(12);

        verify(updateService).enableService(duration);
        verify(view).setUpdateInterval("12");
    }
}