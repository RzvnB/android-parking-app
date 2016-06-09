package com.example.oc.parkingapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Razvan's on 18/May/16.
 */
public class Parking implements Parcelable {

    private String name, address;
    private String openSpots;

    public Parking() {}

    public Parking(String name, String address, String openSpots) {
        this.name = name;
        this.address = address;
        this.openSpots = openSpots;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenSpots() {
        return openSpots;
    }

    public void setOpenSpots(String openSpots) {
        this.openSpots = openSpots;
    }

    public Parking(Parcel in) {
        String[] data = new String[3];

        in.readStringArray(data);
        this.name = data[0];
        this.address = data[1];
        this.openSpots = data[2];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name, this.address, this.openSpots});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Parking createFromParcel(Parcel in) {
            return new Parking(in);
        }

        public Parking[] newArray(int size) {
            return new Parking[size];
        }
    };
}
