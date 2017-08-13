package com.ikholopov.yamblz.weather.weathermobilization.model.network.api;

import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.model.Forecast;
import com.ikholopov.yamblz.weather.weathermobilization.model.WeatherState;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.dto.weather.ForecastInfo;

import org.threeten.bp.Instant;

import io.reactivex.functions.Function;

/**
 * Created by igor on 7/16/17.
 */

public class ForecastMapperProvider {

    @WeatherState.States
    public static int mapWeatherState(int apiWeatherId) {
        switch (apiWeatherId / 100){
            case 2:
                return WeatherState.THUNDERSTORMS;
            case 3:
                return WeatherState.DRIZZLE;
            case 5:
                return WeatherState.SLIGHT_DRIZZLE;
            case 6:
                return WeatherState.SNOW;
            case 7:
                return WeatherState.HAZE;
            case 8:
                if(apiWeatherId == 800) {
                    return WeatherState.SUNNY;
                } else if(apiWeatherId == 801) {
                    return WeatherState.MOSTLY_CLOUDY;
                } else {
                    return WeatherState.CLOUDY;
                }

            case 9:
                return apiWeatherId >= 951 && apiWeatherId <= 957
                        ? WeatherState.MOSTLY_CLOUDY
                        : WeatherState.THUNDERSTORMS;
            default:
                return WeatherState.MOSTLY_CLOUDY;
        }
    }

    Function<ForecastInfo, Forecast> provideMapper(final City city) {
        return info -> new Forecast(
                Instant.ofEpochSecond(info.utcDatetimeSeconds),
                info.main.temp,
                info.main.pressure,
                info.wind.speed,
                info.wind.deg,
                new WeatherState(
                        mapWeatherState(info.weatherStates[0].id),
                        info.weatherStates[0].description),
                city);
    }
}
