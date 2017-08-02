package com.ikholopov.yamblz.weather.weathermobilization.data;

import java.util.Locale;

/**
 * Created by turist on 02.08.2017.
 */

public class LanguageProvider {

    public String getLanguageCode() {
        return Locale.getDefault().getLanguage();
    }
}
