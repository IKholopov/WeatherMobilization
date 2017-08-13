package com.ikholopov.yamblz.weather.weathermobilization.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by turist on 04.08.2017.
 */

@Entity(tableName = "cities", indices={@Index(value="place_id", unique=true)})
public class City implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public final String name;

    @ColumnInfo(name = "place_id")
    public final String placeId;

    @Embedded
    public final Coordinates coordinates;

    public City(long id, String name, String placeId, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.placeId = placeId;
        this.coordinates = coordinates;
    }

    @Ignore
    public City(String name, String placeId, Coordinates coordinates) {
        this.name = name;
        this.placeId = placeId;
        this.coordinates = coordinates;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(placeId);
        dest.writeDouble(coordinates.lat);
        dest.writeDouble(coordinates.lng);
    }

    protected City(Parcel in) {
        id = in.readLong();
        name = in.readString();
        placeId = in.readString();
        double lat = in.readDouble();
        double lng = in.readDouble();
        coordinates = new Coordinates(lat, lng);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}