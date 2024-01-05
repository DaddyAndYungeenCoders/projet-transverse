package com.simulator.models;

import java.io.Serializable;

public class CoordsEntity implements Serializable{

    private double latitude;
    private double longitude;

    public CoordsEntity() {
    }

    public CoordsEntity(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}

