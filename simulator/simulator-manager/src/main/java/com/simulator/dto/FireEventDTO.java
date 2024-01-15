
package com.simulator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simulator.models.CoordsEntity;
import com.simulator.models.FireEventEntity;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.sql.Date;


@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    @JsonProperty("sensorId")
    private Long sensorId;
    @JsonProperty("isHandled")
    private boolean isHandled;


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
        return new FireEventEntity(this.id, this.coords, this.realIntensity, this.startDate, this.endDate, this.isReal, this.isHandled, this.sensorId);
    }
}