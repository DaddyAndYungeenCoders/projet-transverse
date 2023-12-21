package com.simulator.webserver.models;

public class SensorEntity {
    
    private String id;
    private CoordsEntity coordsEntity;

    public DetecsEntity(String id, CoordsEntity coordsEntity) {
        this.id = id;
        this.coordsEntity = coordsEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CoordsEntity getCoordsEntity() {
        return coordsEntity;
    }

    public void setCoordsEntity(CoordsEntity coordsEntity) {
        this.coordsEntity = coordsEntity;
    }


}
