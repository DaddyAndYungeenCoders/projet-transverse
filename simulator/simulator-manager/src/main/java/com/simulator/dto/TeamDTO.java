package com.simulator.dto;

import com.simulator.models.TeamEntity;

import java.io.Serializable;




public class TeamDTO extends BaseDTO implements Serializable {
    private Long id;
    private String chief_officier;
    private int stamina;
    private int id_firestation;
    private boolean is_available;
    private double current_latitude;
    private double current_longitude;

    public  TeamEntity toEntity() {

        return new TeamEntity(
                this.id,
                this.chief_officier,
                this.stamina,
                this.id_firestation,
                this.is_available,
                this.current_latitude,
                this.current_longitude
        );
    }
}
