package com.simulator.webserver.models;


public class CoordsEntity {

    float latitude;
    float longitude;


    public CoordsEntity(float latitude, float longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public float getLatitude() {
        return latitude;
    }


    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }


    public float getLongitude() {
        return longitude;
    }


    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    
}
