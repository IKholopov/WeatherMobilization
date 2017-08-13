package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.PreferencesProvider;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.TemperatureFormat;
import com.ikholopov.yamblz.weather.weathermobilization.model.services.UpdateServiceController;
import com.ikholopov.yamblz.weather.weathermobilization.ui.UpdateIntervalFormat;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.SettingsView;

import org.threeten.bp.Duration;

import javax.inject.Inject;

import static com.ikholopov.yamblz.weather.weathermobilization.model.preferences.TemperatureFormat.CELSIUS;

/**
 * Created by turist on 08.08.2017.
 */

public class SettingsPresenter extends RxPresenter {

    private final PreferencesProvider preferences;
    private final UpdateIntervalFormat format;
    private final UpdateServiceController serviceController;
    private SettingsView view;

    @Inject
    public SettingsPresenter(PreferencesProvider preferences, UpdateIntervalFormat format,
                             UpdateServiceController serviceController) {
        this.preferences = preferences;
        this.format = format;
        this.serviceController = serviceController;
    }

    public void bind(SettingsView view) {
        this.view = view;
        view.setTemperatureUnit(preferences.getTemperatureFormat() == CELSIUS);
        view.setUpdateInterval(format.getString(preferences.getAutoUpdateInterval()));

        save(view.temperatureUnitsSwitches()
                .subscribe(this::saveTemperatureUnit));

        save(view.updateIntervalRequests()
                .subscribe(o -> view.showUpdateIntervalDialog(this::saveInterval)));
    }

    @Override
    public void unbind() {
        super.unbind();
        view = null;
    }

    private void saveTemperatureUnit(boolean isCelsius) {
        preferences.putTemperatureFormat(
                isCelsius ? CELSIUS : TemperatureFormat.FAHRENHEIT);
    }

    private void saveInterval(int intervalId) {
        Duration interval = format.getInterval(intervalId);
        preferences.putAutoUpdateInterval(interval);
        view.setUpdateInterval(format.getString(intervalId));

        if(interval.isZero()) {
            serviceController.disableService();
        } else {
            serviceController.enableService(interval);
        }
    }
}