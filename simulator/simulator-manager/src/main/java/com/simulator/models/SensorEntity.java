package com.simulator.models;

import java.io.Serializable;

public class SensorEntity implements Serializable {

    private Long id;
    private CoordsEntity coordsEntity;

    public SensorEntity() {
    }

    public SensorEntity(Long id, CoordsEntity coordsEntity) {
        this.id = id;
        this.coordsEntity = coordsEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoordsEntity getCoordsEntity() {
        return coordsEntity;
    }

    public void setCoordsEntity(CoordsEntity coordsEntity) {
        this.coordsEntity = coordsEntity;
    }
}
