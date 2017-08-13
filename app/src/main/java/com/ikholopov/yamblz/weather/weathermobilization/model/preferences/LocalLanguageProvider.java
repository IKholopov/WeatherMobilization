package com.ikholopov.yamblz.weather.weathermobilization.model.preferences;

import java.util.Locale;

/**
 * Created by turist on 07.08.2017.
 */

public class LocalLanguageProvider implements LanguageProvider {
    @Override
    public String getLanguageCode() {
        return Locale.getDefault().getLanguage();
    }
}