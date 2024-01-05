package com.simulator.models;

import java.util.Date;


public class HistoriqueEntity {
    private UserEntity user;
    private FireEventEntity fire_event;
    private Date created_date;

    public HistoriqueEntity() {
    }

    public HistoriqueEntity(UserEntity user, FireEventEntity fire_event, Date created_date) {
        this.user = user;
        this.fire_event = fire_event;
        this.created_date = created_date;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public FireEventEntity getFire_event() {
        return fire_event;
    }

    public void setFire_event(FireEventEntity fire_event) {
        this.fire_event = fire_event;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
}

