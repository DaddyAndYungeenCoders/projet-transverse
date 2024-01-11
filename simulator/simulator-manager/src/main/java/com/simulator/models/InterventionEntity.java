package com.simulator.models;

import com.simulator.repository.DetectsRepository;

import java.util.List;

public class InterventionEntity {
    private TeamEntity team;
    private FireEventEntity fire_event;
    private Long duration;

    public InterventionEntity() {
    }

    public InterventionEntity(TeamEntity team, FireEventEntity fire_event, Long duration) {
        this.team = team;
        this.fire_event = fire_event;
        this.duration = duration;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public FireEventEntity getFire_event() {
        return fire_event;
    }

    public void setFire_event(FireEventEntity fire_event) {
        this.fire_event = fire_event;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

}
