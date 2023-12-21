package com.simulator.webserver.models;

import com.simulator.webserver.dto.DetecsDTO;

public class DetecsEntity {
    private SensorEntity sensorEntity;
    private FireEventEntity fireEventEntity;
    private float intensity;

    public DetecsEntity(SensorEntity sensorEntity, FireEventEntity fireEventEntity, float intensity) {
        this.sensorEntity = sensorEntity;
        this.fireEventEntity = fireEventEntity;
        this.intensity = intensity;
    }

    public DetecsDTO toDTO(){
        return DetecsDTO(this.sensorEntity.getId(), this.intensity);
    }

    public SensorEntity getSensorEntity() {
        return sensorEntity;
    }

    public void setSensorEntity(SensorEntity sensorEntity) {
        this.sensorEntity = sensorEntity;
    }

    public FireEventEntity getFireEventEntity() {
        return fireEventEntity;
    }

    public void setFireEventEntity(FireEventEntity fireEventEntity) {
        this.fireEventEntity = fireEventEntity;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    
}
