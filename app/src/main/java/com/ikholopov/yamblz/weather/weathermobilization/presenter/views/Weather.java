package com.ikholopov.yamblz.weather.weathermobilization.presenter.views;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.model.Forecast;
import com.ikholopov.yamblz.weather.weathermobilization.model.WeatherState;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.TemperatureFormat;

import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Created by turist on 13.08.2017.
 */

public class Weather {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM HH:mm");

    public final String dateTime;
    public final double temperature;
    public final double pressure;
    public final double windSpeed;
    public final double windDegree;
    public final String description;

    @StringRes
    public final int temperatureUnit;
    @DrawableRes
    public final int weatherImageId;

    public Weather(Forecast forecast, TemperatureFormat format) {
        this.dateTime = forecast.dateTime.atZone(ZoneId.systemDefault())
                .toLocalDateTime().format(DATE_TIME_FORMATTER);

        this.temperatureUnit = format.unitStringId();
        this.temperature = format.convert(forecast.celsiusTemperature);
        this.pressure = forecast.pressure;
        this.windSpeed = forecast.windSpeed;
        this.windDegree = forecast.windDegree;
        this.description = forecast.state.description;
        this.weatherImageId = stateToImageId(forecast.state.state);
    }

    @DrawableRes
    int stateToImageId(@WeatherState.States int state) {
        switch (state) {
            case WeatherState.CLOUDY:
                return R.drawable.cloudy;
            case WeatherState.DRIZZLE:
                return R.drawable.drizzle;
            case WeatherState.HAZE:
                return R.drawable.haze;
            case WeatherState.MOSTLY_CLOUDY:
                return R.drawable.mostly_cloudy;
            case WeatherState.SLIGHT_DRIZZLE:
                return R.drawable.slight_drizzle;
            case WeatherState.SNOW:
                return R.drawable.snow;
            case WeatherState.SUNNY:
                return R.drawable.sunny;
            case WeatherState.THUNDERSTORMS:
                return R.drawable.thunderstorms;

            default:
                return R.drawable.cloudy;
        }
    }
}