package com.simulator.models.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simulator.models.CoordsEntity;
import com.simulator.models.FireEventEntity;
import com.simulator.service.FireEventService;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class FireEventDatabaseEntity {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("coords")
    private CoordsEntity coords;

    @JsonProperty("real_intensity")
    private int real_intensity;

    @JsonProperty("start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.util.Date start_date;

    @JsonProperty("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.util.Date end_date;

    @JsonProperty("sensorId")
    private Long sensorId;

    @JsonProperty("_real")
    private boolean is_real;

    @JsonProperty("_handled")
    private boolean is_handled;
    
    public FireEventDatabaseEntity() {};

    public FireEventDatabaseEntity(Long id, CoordsEntity coords, int realIntensity, Date startDate, Date endDate, Long sensorId, boolean isReal, boolean isHandled) {
        this.id = id;
        this.coords = coords;
        this.real_intensity = realIntensity;
        this.start_date = startDate;
        this.end_date = endDate;
        this.sensorId = sensorId;
        this.is_real = isReal;
        this.is_handled = isHandled;
    }

    public FireEventEntity toEntity() {
        return new FireEventEntity(
                this.id,
                this.coords,
                this.real_intensity,
                FireEventService.convertUtilToSqlDate(this.start_date),
                null,
                this.is_real,
                this.is_handled,
                this.sensorId
        );
    }

}
