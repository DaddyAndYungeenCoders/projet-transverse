
package com.datacenter.webserver.dto;

import com.datacenter.webserver.models.BaseEntity;
import com.datacenter.webserver.models.Coords;
import com.datacenter.webserver.models.FireEventEntity;
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

    @Override
    public FireEventEntity toEntity() {
        return new FireEventEntity(this.id, this.coords, this.realIntensity, this.startDate, this.endDate, this.isReal);
    }
}