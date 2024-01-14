package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Team {
    private int id;
    private String chiefOfficer;
    private int stamina;
    private int fireStationId;
    private boolean isAvailable;
    private Coords coords;

    @JsonCreator
    public Team(@JsonProperty("id") int id,
                @JsonProperty("stamina") int stamina,
                @JsonProperty("chief_officer") String chiefOfficer,
                @JsonProperty("id_firestation") int fireStationId,
                @JsonProperty("is_available") boolean isAvailable,
                @JsonProperty("coords") Coords coords) {
        this.id = id;
        this.chiefOfficer = chiefOfficer;
        this.stamina = stamina;
        this.fireStationId = fireStationId;
        this.isAvailable = isAvailable;
        this.coords = coords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getChiefOfficer() {
        return chiefOfficer;
    }

    public void setChiefOfficer(String chiefOfficer) {
        this.chiefOfficer = chiefOfficer;
    }

    public int getFireStationId() {
        return fireStationId;
    }

    public void setFireStationId(int fireStationId) {
        this.fireStationId = fireStationId;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }
}
