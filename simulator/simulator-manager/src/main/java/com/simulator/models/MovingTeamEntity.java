package com.simulator.models;

import com.simulator.service.MovingTeamService;
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
    private boolean moving = true;
    private boolean backingHome = false;

    private int count = 0;

    private static final MovingTeamService movingTeamService = new MovingTeamService();

    public MovingTeamEntity(TeamEntity team, CoordsEntity fireCoords, CoordsEntity fireStationCoords, int stamina, int fireMasteryTotal) {
        this.team = team;
        this.destination = fireCoords;
        this.current_position = fireStationCoords;
        this.stamina = stamina;
        this.fire_mastery_total = fireMasteryTotal;
        movingTeamService.sendTeamPosition(this);
    }

    public void move() {
        if (moving) {
            current_position = destination;
            movingTeamService.sendTeamPosition(this);
            if (isArrived()) {
                moving = false;
            }
        } else {
            count++;
            if (count == 10) {
                System.out.println("Moving team " + team.getId() + " to fire station");
                destination = team.getFire_station().getCoords();
                count = 0;
                moving = true;
                backingHome = true;
            }
        }
    }

    public Boolean isArrived() {
        return current_position.equals(destination);
    }

    public Boolean isBackHome() {
        if (backingHome)
            return current_position.equals(team.getFire_station().getCoords());
        else
            return false;
    }


}
