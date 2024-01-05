package com.simulator.models;

import com.simulator.repository.InterventionRepository;

import java.util.List;

public class TeamEntity {
    private Long id;
    private Long stamina;
    private FireStationEntity fire_station;

    InterventionRepository interventionRepository = new InterventionRepository();
    public TeamEntity() {
    }

    public TeamEntity(Long id, Long stamina, FireStationEntity fire_station) {
        this.id = id;
        this.stamina = stamina;
        this.fire_station = fire_station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStamina() {
        return stamina;
    }

    public void setStamina(Long stamina) {
        this.stamina = stamina;
    }

    public FireStationEntity getFire_station() {
        return fire_station;
    }

    public void setFire_station(FireStationEntity fire_station) {
        this.fire_station = fire_station;
    }

    public List<InterventionEntity> getIntervention() {
        return InterventionRepository.getInterventionsByTeamId(this.id);
    }
}
