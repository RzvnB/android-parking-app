package com.example.oc.parkingapp;

/**
 * Created by Razvan's on 18/May/16.
 */
public class Parking {

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




}
