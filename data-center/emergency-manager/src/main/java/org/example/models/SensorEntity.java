package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SensorEntity implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("coords")
    private Coords coords;
    @JsonProperty("intensity")
    private int intensity;

    public SensorEntity() {
    }

    public SensorEntity(Long id, Coords coords, int intensity) {
        this.id = id;
        this.coords = coords;
        this.intensity = intensity;
    }

//    public SensorDTO toDTO() {
//        SensorDTO sensorDTO = new SensorDTO();
//        sensorDTO.setId(this.id);
//        sensorDTO.setIntensity(this.intensity);
//        return sensorDTO;
//    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
}
