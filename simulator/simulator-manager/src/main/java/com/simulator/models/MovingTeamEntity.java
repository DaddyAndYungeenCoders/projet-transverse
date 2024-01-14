package com.simulator.models;

import com.simulator.dto.InterventionMessageDTO;
import com.simulator.service.FireStationService;
import com.simulator.service.MovingTeamService;
import com.simulator.service.RouteService;
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
    private FireStationEntity fire_station;
    private int stamina;
    private int fire_mastery_total;
    private boolean moving = true;
    private boolean backingHome = false;
    private RouteResponseEntity route;
    private int step = 0;

    private int count = 0;

    private static final MovingTeamService movingTeamService = new MovingTeamService();
    private static final RouteService routeService = new RouteService();
    private static final FireStationService fireStationService = new FireStationService();


    public MovingTeamEntity(TeamEntity team, CoordsEntity fireCoords, CoordsEntity fireStationCoords, int stamina, int fireMasteryTotal, FireStationEntity fireStationEntity) {
        this.team = team;
        this.destination = fireCoords;
        this.current_position = fireStationCoords;
        this.stamina = stamina;
        this.fire_mastery_total = fireMasteryTotal;
        this.route = routeService.getRoute(current_position, destination);
        this.fire_station = fireStationEntity;
        movingTeamService.sendTeamPosition(this.toInterventionMessageDTO());
    }

    public void move() {
        if (moving) {
            this.changePosition();
            movingTeamService.sendTeamPosition(this.toInterventionMessageDTO());
            if (isArrived()) {
                moving = false;
            }
        } else {
            count++;
            if (count == 10) {
                System.out.println("Moving team " + team.getId() + " to fire station");
                destination = fire_station.getCoords();
                this.route = routeService.getRoute(current_position, destination);
                this.step = 0;
                count = 0;
                moving = true;
                backingHome = true;
            }
        }
    }

    private void changePosition(){
        step++;
        CoordsEntity newStep = new CoordsEntity();
        Double latitude = this.route.getRoutes().get(0).getLegs().get(0).getSteps().get(step).getIntersections().get(0).getLocation().get(1);
        Double longitude = this.route.getRoutes().get(0).getLegs().get(0).getSteps().get(step).getIntersections().get(0).getLocation().get(0);
        newStep.setLatitude(latitude);
        newStep.setLongitude(longitude);
        if (step == this.route.getRoutes().get(0).getLegs().get(0).getSteps().size() -1 ) {
            current_position = destination;
            step = 0;
        } else {
            current_position = newStep;

        }
    }

    public Boolean isArrived() {
        return current_position.equals(destination);
    }

    public Boolean isBackHome() {
        if (backingHome)
            return current_position.equals(fire_station.getCoords());
        else
            return false;
    }

    public InterventionMessageDTO toInterventionMessageDTO() {
        InterventionMessageDTO interventionMessageDTO = new InterventionMessageDTO();
        interventionMessageDTO.setTeam_id(team.getId());
        interventionMessageDTO.setFire_mastery_total(fire_mastery_total);
        interventionMessageDTO.setStamina(stamina);
        interventionMessageDTO.setCoords(current_position);
        interventionMessageDTO.setFire_station_id(this.fire_station.getId());
        return interventionMessageDTO;
    }


}
