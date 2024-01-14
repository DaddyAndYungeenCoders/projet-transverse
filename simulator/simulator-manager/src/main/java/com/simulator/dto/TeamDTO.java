package com.simulator.dto;

import com.simulator.models.TeamEntity;

import java.io.Serializable;


public class TeamDTO extends BaseDTO implements Serializable {
    private Long id;
    private int stamina;
    private int id_firestation;
    private int fire_mastery;

    public TeamEntity toEntity() {
        return new TeamEntity(this.id, this.stamina, this.id_firestation, this.fire_mastery);
    }
}
