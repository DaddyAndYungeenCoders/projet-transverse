
package com.simulator.webserver.dto;

import com.simulator.webserver.models.Coords;
import com.simulator.webserver.models.FireEventEntity;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FireEventDTO extends BaseDTO implements Serializable {
    private Long id;
    private Coords coords;
    private int realIntensity;
    private Date startDate;
    private Date endDate;
    private boolean isReal;
    private boolean isHandled;
    private Long sensorId;

    public boolean hasNecessaryValuesToCreate() {
        return (
                this.endDate == null
                        && this.realIntensity > 0
                        && this.startDate != null
                        && !ObjectUtils.isEmpty(this.coords)
        );
    }
    
    public FireEventEntity toEntity() {
        return new FireEventEntity(this.id, this.coords, this.realIntensity, this.startDate, this.endDate, this.isReal, this.isHandled, this.sensorId);
    }
}