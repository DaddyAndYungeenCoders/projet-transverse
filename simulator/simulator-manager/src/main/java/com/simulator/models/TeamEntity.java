package com.simulator.models;


import java.util.List;

public class TeamEntity {
    private Long id;
    private String chief_officier;
    private int stamina;
    private int id_firestation;
    private boolean is_available;
    private double current_latitude;
    private double current_longitude;



    public TeamEntity( Long id,
     String chief_officier,
     int stamina,
     int id_firestation,
     boolean is_available,
     double current_latitude,
     double current_longitude) {
        this.id = id;
        this.chief_officier=chief_officier;
        this.stamina = stamina;
        this.id_firestation=id_firestation;
        this.is_available=is_available;
        this.current_latitude=current_latitude;
        this.current_longitude=current_longitude;

    }


    public Long getId() {
        return id;
    }
}
