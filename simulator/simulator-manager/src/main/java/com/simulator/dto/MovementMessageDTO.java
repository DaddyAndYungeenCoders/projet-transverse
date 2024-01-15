package com.simulator.dto;

import com.simulator.models.CoordsEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovementMessageDTO extends BaseDTO {
    private CoordsEntity coords;
    private int stamina;
    private Long fire_station_id;
    private Long team_id;
}
