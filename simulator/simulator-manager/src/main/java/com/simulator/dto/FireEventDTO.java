
package com.simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simulator.models.CoordsEntity;
import com.simulator.models.FireEventEntity;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.sql.Date;

public class FireEventDTO extends BaseDTO implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("coords")
    private CoordsEntity coords;
    @JsonProperty("realIntensity")
    private int realIntensity;
    @JsonProperty("startDate")
    private Date startDate;
    @JsonProperty("endDate")
    private Date endDate;
    @JsonProperty("real")
    private boolean isReal;


    public boolean hasNecessaryValuesToCreate() {
        return (
                this.endDate == null
                        && this.realIntensity > 0
                        && this.startDate != null
                        && !ObjectUtils.isEmpty(this.coords)
                        && this.id != null
        );
    }

    public FireEventEntity toEntity(){
        return new FireEventEntity(this.id, this.coords, this.realIntensity, this.startDate, this.endDate, this.isReal);
    }
}