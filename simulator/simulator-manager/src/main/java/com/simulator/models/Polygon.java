package com.simulator.models;

import java.util.List;

public class Polygon {
    private String type;
    private List<List<Double>> coordinates;

    // Constructeurs, getters et setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }


    @Override
    public String toString() {
        return "Polygon{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}