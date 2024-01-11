package com.simulator.models;

import com.simulator.repository.DetectsRepository;

import java.io.Serializable;
import java.util.List;

public class SensorEntity implements Serializable {

    private Long id;
    private CoordsEntity coordsEntity;

    DetectsRepository detectsRepository = new DetectsRepository();
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

    public List<DetectsEntity> getDetects() {
        return detectsRepository.getDetectsBySensorId(this.id);
    }
}
