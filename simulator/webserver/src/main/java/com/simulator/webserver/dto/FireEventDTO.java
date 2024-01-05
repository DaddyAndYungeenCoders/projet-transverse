
package com.simulator.webserver.dto;

import com.simulator.webserver.models.Coords;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class FireEventDTO {
    private int id;
    private Coords coords;
    private int realIntensity;
    private Date startDate;
    private Date endDate;
    private boolean isReal;

    public boolean hasNecessaryValuesToCreate() {
        return (
                this.endDate == null
                        && this.realIntensity > 0
                        && this.startDate != null
                        && !ObjectUtils.isEmpty(this.coords)
        );
    }
}