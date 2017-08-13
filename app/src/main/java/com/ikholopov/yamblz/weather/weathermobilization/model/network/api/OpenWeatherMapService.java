package com.ikholopov.yamblz.weather.weathermobilization.model.network.api;

import com.ikholopov.yamblz.weather.weathermobilization.di.StringQualifier;
import com.ikholopov.yamblz.weather.weathermobilization.model.City;
import com.ikholopov.yamblz.weather.weathermobilization.model.Forecast;
import com.ikholopov.yamblz.weather.weathermobilization.model.preferences.LanguageProvider;
import com.ikholopov.yamblz.weather.weathermobilization.model.network.WeatherService;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.ikholopov.yamblz.weather.weathermobilization.di.StringQualifier.OPEN_WEATHER_MAP_API_KEY;

/**
 * Created by turist on 28.07.2017.
 */

public class OpenWeatherMapService implements WeatherService {

    private static final String UNITS = "metric";

    private final String apiKey;
    private final OpenWeatherMapApi api;
    private final LanguageProvider languageProvider;
    private final ForecastMapperProvider weatherMapper;

    @Inject
    public OpenWeatherMapService(@StringQualifier(stringType = OPEN_WEATHER_MAP_API_KEY) String apiKey,
                                 OpenWeatherMapApi api,
                                 LanguageProvider languageProvider,
                                 ForecastMapperProvider weatherMapper) {
        this.apiKey = apiKey;
        this.api = api;
        this.languageProvider = languageProvider;
        this.weatherMapper = weatherMapper;
    }

    @Override
    public Observable<Forecast> forecasts(final City city) {
        return api.forecast(apiKey, city.coordinates.lat, city.coordinates.lng, UNITS, languageProvider.getLanguageCode())
                .flatMapObservable(response -> Observable.fromArray(response.list))
                .map(weatherMapper.provideMapper(city));
    }
}