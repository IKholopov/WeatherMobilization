package com.ikholopov.yamblz.weather.weathermobilization.model.persistence.db;

import android.arch.persistence.room.TypeConverter;

import org.threeten.bp.Instant;

/**
 * Created by turist on 06.08.2017.
 */

public class Converters {

    @TypeConverter
    public static Instant fromTimestamp(Long value) {
        return Instant.ofEpochSecond(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Instant date) {
        return date.getEpochSecond();
    }
}