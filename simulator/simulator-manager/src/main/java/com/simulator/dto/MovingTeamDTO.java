package com.simulator.dto;

import com.simulator.models.CoordsEntity;
import com.simulator.models.TeamEntity;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovingTeamDTO {
    private long team;
    private CoordsEntity destination;
    private CoordsEntity current_position;
    private int stamina;
    private int fire_mastery_total;

}
