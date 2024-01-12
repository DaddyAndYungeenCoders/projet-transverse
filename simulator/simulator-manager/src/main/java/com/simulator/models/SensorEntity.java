package com.simulator.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simulator.repository.DetectsRepository;

import java.io.Serializable;
import java.util.List;

public class SensorEntity implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("coords")
    private CoordsEntity coordsEntity;
    @JsonProperty("intensity")
    private int intensity;

    DetectsRepository detectsRepository = new DetectsRepository();
    public SensorEntity() {
    }

    public SensorEntity(Long id, CoordsEntity coordsEntity, int intensity) {
        this.id = id;
        this.coordsEntity = coordsEntity;
        this.intensity = intensity;
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

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public void setCoords(CoordsEntity coords) {
        this.coordsEntity = coords;
    }
}
