package com.simulator.webserver.models;

public class SensorEntity {
    
    private int id;
    private CoordsEntity coordsEntity;

    public SensorEntity(int id, CoordsEntity coordsEntity) {
        this.id = id;
        this.coordsEntity = coordsEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CoordsEntity getCoordsEntity() {
        return coordsEntity;
    }

    public void setCoordsEntity(CoordsEntity coordsEntity) {
        this.coordsEntity = coordsEntity;
    }


}
