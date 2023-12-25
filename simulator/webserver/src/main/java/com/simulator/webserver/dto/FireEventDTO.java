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

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        this.realIntensity = 0;
    }

    public void setRealIntensity(int realIntensity) {
        this.realIntensity = realIntensity;
        if (this.realIntensity == 0) this.endDate = Date.valueOf(LocalDate.now());
    }

    public boolean hasNecessaryValuesToCreate() {
        System.out.println("Is Valid ? " + this.endDate == null && this.realIntensity > 0  && this.startDate != null && !ObjectUtils.isEmpty(this.coords));
        return (
                this.endDate == null
                        && this.realIntensity > 0
                        && this.startDate != null
                        && !ObjectUtils.isEmpty(this.coords)
                );
    }
}
