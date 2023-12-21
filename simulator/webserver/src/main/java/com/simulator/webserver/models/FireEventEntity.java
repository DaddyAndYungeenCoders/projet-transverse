package com.simulator.webserver.models;

import java.sql.Date;

public class FireEventEntity {
    private String id;
    private CoordsEntity coords;
    private int real_intensity;
    private Date start_date;
    private Date end_date;
    private boolean is_real;

    public FireEventEntity(String id, CoordsEntity coords, int real_intensity, 
            Date start_date, Date end_date, boolean is_real) {
        this.id = id;
        this.coords = coords;
        this.real_intensity = real_intensity;
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_real = is_real;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CoordsEntity getCoords() {
        return coords;
    }

    public void setCoords(CoordsEntity coords) {
        this.coords = coords;
    }

    public int getReal_intensity() {
        return real_intensity;
    }

    public void setReal_intensity(int real_intensity) {
        this.real_intensity = real_intensity;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public boolean isIs_real() {
        return is_real;
    }

    public void setIs_real(boolean is_real) {
        this.is_real = is_real;
    }

    

}
