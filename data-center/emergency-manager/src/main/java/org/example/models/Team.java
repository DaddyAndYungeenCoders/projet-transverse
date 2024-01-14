package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Team {
    private Long id;
    private String chiefOfficer;
    private int stamina;
    private Long fireStationId;
    private boolean isAvailable;
    private Coords coords;

    @JsonCreator
    public Team(@JsonProperty("id") Long id,
                @JsonProperty("stamina") int stamina,
                @JsonProperty("chief_officer") String chiefOfficer,
                @JsonProperty("idFirestation") Long fireStationId,
                @JsonProperty("is_available") boolean isAvailable,
                @JsonProperty("coords") Coords coords) {
        this.id = id;
        this.chiefOfficer = chiefOfficer;
        this.stamina = stamina;
        this.fireStationId = fireStationId;
        this.isAvailable = isAvailable;
        this.coords = coords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getFireStationId() {
        return fireStationId;
    }

    public void setFireStationId(Long fireStationId) {
        this.fireStationId = fireStationId;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }
}
