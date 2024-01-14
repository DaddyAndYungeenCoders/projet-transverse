
package com.datacenter.webserver.dto;

import com.datacenter.webserver.models.Coords;
import com.datacenter.webserver.models.FireEventEntity;
import com.datacenter.webserver.models.ValidationStatus;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.sql.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FireEventDTO extends BaseDTO implements Serializable {
    private Long id;
    private Coords coords;
    private int intensity;
    private Date startDate;
    private Date endDate;
    private boolean isVerified;
    private Long sensorId;
    private Long idEquipeIntervention;
    private boolean isHandled;

    public boolean hasNecessaryValuesToCreate() {
        return (
                this.endDate == null
                        && this.intensity > 0
                        && this.startDate != null
                        && !ObjectUtils.isEmpty(this.coords)
        );
    }

    @Override
    public FireEventEntity toEntity() {
        return new FireEventEntity(this.id, this.coords, this.intensity, this.startDate, this.endDate, this.isVerified, this.sensorId, this.idEquipeIntervention, this.isHandled);
    }
}