package com.simulator.models;


import java.util.List;

public class FireStationEntity {
    private Long id;
    private String name;
    private String address;
    private CoordsEntity coords;

    public FireStationEntity() {
    }

    public FireStationEntity(Long id, String name, String address, CoordsEntity coords) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.coords = coords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CoordsEntity getCoords() {
        return coords;
    }

    public void setCoords(CoordsEntity coords) {
        this.coords = coords;
    }

    public List<TeamEntity> getTeams() {
        return null;
    }
}
