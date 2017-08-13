package com.ikholopov.yamblz.weather.weathermobilization.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.SettingsPresenter;
import com.ikholopov.yamblz.weather.weathermobilization.ui.MainActivity;
import com.ikholopov.yamblz.weather.weathermobilization.ui.rx.RxSingleToggleGroup;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.views.SettingsView;
import com.jakewharton.rxbinding2.view.RxView;
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * {@link Fragment} with settings.
 */
public class SettingsFragment extends KnifeFragment implements SettingsView {

    @BindView(R.id.temperature_unit_button) SingleSelectToggleGroup temperatureUnitButton;
    @BindView(R.id.update_interval_label) TextView updateIntervalButton;
    @BindView(R.id.update_interval_value) TextView updateInterval;

    @Inject SettingsPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getViewComponent().inject(this);
        View view = bind(inflater.inflate(R.layout.fragment_settings, container, false));
        presenter.bind(this);
        return view;
    }

    @Override
    public void setTemperatureUnit(boolean isCelsius) {
        temperatureUnitButton.check(isCelsius ? R.id.choice_c : R.id.choice_f);
    }

    @Override
    public void setUpdateInterval(String interval) {
        updateInterval.setText(interval);
    }

    @Override
    public void showUpdateIntervalDialog(final Consumer<Integer> intervalListener) {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.update_interval)
                .setItems(R.array.update_intervals, (dialog, which) -> {
                    try {
                        intervalListener.accept(which);
                    } catch (Exception e) {
                        Log.e("SettingsFragment", "save update interval " + which, e);
                    }
                })
                .create()
                .show();
    }

    @Override
    public Observable<Boolean> temperatureUnitsSwitches() {
        return RxSingleToggleGroup.selections(temperatureUnitButton)
                .map(id -> id == R.id.choice_c);
    }

    @Override
    public Observable<Object> updateIntervalRequests() {
        return RxView.clicks(updateIntervalButton);
    }

    @OnClick(R.id.back_button)
    void navigateBack() {
        getActivity().onBackPressed();
    }
}