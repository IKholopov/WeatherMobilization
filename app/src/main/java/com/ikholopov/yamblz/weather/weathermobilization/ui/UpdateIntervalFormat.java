package com.ikholopov.yamblz.weather.weathermobilization.ui;

import android.content.Context;

import com.ikholopov.yamblz.weather.weathermobilization.R;

import org.threeten.bp.Duration;

import javax.inject.Inject;

/**
 * Created by turist on 11.08.2017.
 */

public class UpdateIntervalFormat {

    private final Context context;

    @Inject
    public UpdateIntervalFormat(Context context) {
        this.context = context;
    }

    public String getString(Duration interval) {
        long minutes = interval.toMinutes();
        if(minutes < 10) {
            return getString(0);
        }
        if(minutes < 20) {
            return getString(1);
        }
        if(minutes < 50) {
            return getString(2);
        }
        if(minutes < 80) {
            return getString(3);
        }

        return getString(4);
    }

    public String getString(int intervalId) {
        String[] intervals = context.getResources()
                .getStringArray(R.array.update_intervals);

        if(intervalId < 5) {
            return intervals[intervalId];
        }

        return intervals[4];
    }

    public Duration getInterval(int intervalId) {
        switch (intervalId) {
            case 0:
                return Duration.ZERO;
            case 1:
                return Duration.ofMinutes(15);
            case 2:
                return Duration.ofMinutes(30);
            case 3:
                return Duration.ofHours(1);
            default:
                return Duration.ofHours(2);
        }
    }
}