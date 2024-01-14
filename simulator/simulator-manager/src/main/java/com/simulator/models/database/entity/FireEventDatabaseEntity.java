package com.simulator.models.database.entity;

import com.simulator.models.CoordsEntity;
import com.simulator.models.FireEventEntity;

import java.sql.Date;

public class FireEventDatabaseEntity {
    private Long id;
    private CoordsEntity coords;
    private int real_intensity;
    private Date start_date;
    private Date end_date;

    // Peut etre un probleme car autoconversion qui eneleve le is !
    private boolean is_real;

    // Peut etre un probleme car autoconversion qui eneleve le is !
    private boolean is_handled;


    public FireEventDatabaseEntity(Long id, CoordsEntity coords, int realIntensity, Date startDate, Date endDate, boolean isReal, boolean isHandled) {
        this.id = id;
        this.coords = coords;
        this.real_intensity = realIntensity;
        this.start_date = startDate;
        this.end_date = endDate;
        this.is_real = isReal;
        this.is_handled = isHandled;
    }

    public FireEventEntity toEntity() {
        return new FireEventEntity(
                this.id,
                this.coords,
                this.real_intensity,
                this.start_date,
                this.end_date,
                this.is_real,
                this.is_handled
        );
    }

}
