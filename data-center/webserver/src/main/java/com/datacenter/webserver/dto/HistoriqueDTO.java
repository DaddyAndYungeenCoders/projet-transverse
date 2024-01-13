package com.datacenter.webserver.dto;

import com.datacenter.webserver.models.HistoriqueEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class HistoriqueDTO extends BaseDTO {
    private Long sensorId;
    private Long fireEventId;
    private Date createdDate;
    
    @Override
    public HistoriqueEntity toEntity() {
        return new HistoriqueEntity(this.sensorId, this.fireEventId, this.createdDate);
    }
}
