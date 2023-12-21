package com.simulator.webserver.models;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SensorEntity {
    private String id;
    private float intensity;

    public SensorEntity(String id, float intensity) {
        this.id = id;
        this.intensity = intensity;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    public String getId() {
        return id;
    }

    public float getIntensity() {
        return intensity;
    }
}
