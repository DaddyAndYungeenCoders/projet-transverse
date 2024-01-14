package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorDetection {
    private int id;
    private int intensity;
    private boolean isReal;

    @JsonCreator
    public SensorDetection(@JsonProperty("id") int id,
                           @JsonProperty("intensity") int intensity,
                           @JsonProperty("is_real") boolean isReal) {
        this.id = id;
        this.intensity = intensity;
        this.isReal = isReal;
    }

    public boolean isReal() {
        return isReal;
    }

    public void setReal(boolean real) {
        isReal = real;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    @Override
    public String toString() {
        return "FireEvent{" +
                "id='" + id + '\'' +
                ", intensity=" + intensity +
                ", isReal=" + isReal +
                '}';
    }
}
