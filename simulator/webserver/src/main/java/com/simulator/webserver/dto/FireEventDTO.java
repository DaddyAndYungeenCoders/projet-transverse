
package com.simulator.webserver.dto;

import com.simulator.webserver.models.Coords;
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
    private boolean is_handled;

    public boolean hasNecessaryValuesToCreate() {
        return (
                this.endDate == null
                        && this.realIntensity > 0
                        && this.startDate != null
                        && !ObjectUtils.isEmpty(this.coords)
        );
    }
}