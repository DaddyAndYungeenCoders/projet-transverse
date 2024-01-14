package com.simulator.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TeamEntity {
    private Long id;
    private int stamina;
    private int idFirestation;
    private int fireMastery;


    public TeamEntity(Long id,
                      int stamina,
                      int idFirestation,
                      int fireMastery
    ) {
        this.id = id;
        this.stamina = stamina;
        this.fireMastery = fireMastery;

    }


    public Long getId() {
        return id;
    }

    public int getStamina() {
        return stamina;
    }

    public int getIdFirestation() {
        return idFirestation;
    }

    public int getFireMastery() {
        return fireMastery;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setIdFirestation(int idFirestation) {
        this.idFirestation = idFirestation;
    }

    public void setFireMastery(int fireMastery) {
        this.fireMastery = fireMastery;
    }
}
