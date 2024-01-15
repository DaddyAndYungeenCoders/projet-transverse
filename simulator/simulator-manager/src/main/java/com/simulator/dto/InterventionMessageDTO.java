package com.simulator.dto;

import com.simulator.models.CoordsEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InterventionMessageDTO extends BaseDTO {
    private CoordsEntity coords;
    private int stamina;
    private int fire_mastery_total;
    private Long fire_station_id;
    private Long team_id;
    private Long sensor_id;
}
