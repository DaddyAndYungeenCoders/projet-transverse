package com.simulator.models;

import com.simulator.dto.MovingTeamDTO;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovingTeamEntity {
    private TeamEntity team;
    private CoordsEntity destination;
    private CoordsEntity current_position;
    private int stamina;
    private int fire_mastery_total;

    public void move() {
        current_position = destination;
    }

    public Boolean isArrived() {
        return current_position.equals(destination);
    }


}
