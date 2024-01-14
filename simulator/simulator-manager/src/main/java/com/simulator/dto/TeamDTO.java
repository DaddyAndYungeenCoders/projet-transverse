package com.simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simulator.models.TeamEntity;

import java.io.Serializable;


public class TeamDTO extends BaseDTO implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("stamina")
    private int stamina;
    @JsonProperty("fire_station_id")
    private int id_firestation;
    @JsonProperty("fire_mastery")
    private int fire_mastery;

    public TeamEntity toEntity() {
        return new TeamEntity(this.id, this.stamina, this.id_firestation, this.fire_mastery);
    }
}
