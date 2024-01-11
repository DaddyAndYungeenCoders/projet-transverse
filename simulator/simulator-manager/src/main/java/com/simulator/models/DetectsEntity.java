package com.simulator.models;

public class DetectsEntity {
    private SensorEntity sensorEntity;
    private FireEventEntity fireEventEntity;
    private float intensity;

//    public DetectsDTO toDTO(){
//        return new DetectsDTO(this.sensorEntity.getId(), this.intensity);
//    }

    public DetectsEntity() {
    }

    public DetectsEntity(SensorEntity sensorEntity, FireEventEntity fireEventEntity, float intensity) {
        this.sensorEntity = sensorEntity;
        this.fireEventEntity = fireEventEntity;
        this.intensity = intensity;
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

