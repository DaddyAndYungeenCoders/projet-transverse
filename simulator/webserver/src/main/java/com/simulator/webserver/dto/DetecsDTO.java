package com.simulator.webserver.dto;



public class DetecsDTO {
    private int id;
    private float intensity;

    public DetecsDTO(int id, float intensity) {
        this.id = id;
        this.intensity = intensity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }


}
