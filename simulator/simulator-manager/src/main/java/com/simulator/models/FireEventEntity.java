package com.simulator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class FireEventEntity implements Serializable{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("coords")
    private CoordsEntity coords;

    @JsonProperty("real_intensity")
    private int realIntensity;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("end_date")
    private Date endDate;

    @JsonProperty("is_real")
    private boolean isReal;

    @JsonProperty("is_handled")
    private boolean isHandled;


    public FireEventEntity() {
    }

    public FireEventEntity(Long id, CoordsEntity coords, int realIntensity, Date startDate, Date endDate, boolean isReal, boolean isHandled) {
        this.id = id;
        this.coords = coords;
        this.realIntensity = realIntensity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isReal = isReal;
        this.isHandled = isHandled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoordsEntity getCoords() {
        return coords;
    }

    public void setCoords(CoordsEntity coords) {
        this.coords = coords;
    }

    public int getRealIntensity() {
        return realIntensity;
    }

    public void setRealIntensity(int realIntensity) {
        this.realIntensity = realIntensity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isReal() {
        return isReal;
    }

    public void setReal(boolean real) {
        this.isReal = real;
    }

    public Boolean getFireState(){
        return this.isHandled;
    }

    public List<InterventionEntity> getIntervention() {
return null;
    }

    public DetectsEntity getDetecs() {
        return null;
    }
}
