package com.simulator.models;

import com.simulator.repository.DetectsRepository;

import java.io.Serializable;
import java.util.List;

public class SensorEntity implements Serializable {

    private Long id;
    private CoordsEntity coordsEntity;
    private Interger intensite;

    DetectsRepository detectsRepository = new DetectsRepository();
    public SensorEntity() {
    }

    public SensorEntity(Long id, CoordsEntity coordsEntity, Integer intensite) {
        this.id = id;
        this.coordsEntity = coordsEntity;
        this.intensite = intensite;
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

    public Interger getIntensite() {
        return intensite;
    }

    public void setIntensite(Interger intensite) {
        this.intensite = intensite;
    }
}
