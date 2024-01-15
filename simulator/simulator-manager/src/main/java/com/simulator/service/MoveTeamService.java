package com.simulator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.models.MovingTeamEntity;
import com.simulator.utils.Topics;

public class MoveTeamService implements Runnable {
    private MovingTeamEntity movingTeamEntity;
    private MovingTeamService movingTeamService = new MovingTeamService();
    private FireEventService fireEventService = new FireEventService();
    private static final RouteService routeService = new RouteService();
    private static final MQTTService mqttService = new MQTTService();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public MoveTeamService(MovingTeamEntity movingTeamEntity) {
        this.movingTeamEntity = movingTeamEntity;
    }
    @Override
    public void run() {
        if (movingTeamEntity.isMoving()) {
            movingTeamEntity.changePosition();
            movingTeamService.sendTeamPosition(movingTeamEntity.toInterventionMessageDTO());
            if (movingTeamEntity.isArrived()) {
                System.out.println("Arrived");

                movingTeamEntity.setMoving(false);
                if(movingTeamEntity.isBackingHome()){
                   //intervention terminé
                    try {
                        String json = objectMapper.writeValueAsString(movingTeamEntity.getFireEvent());
                        mqttService.publish(Topics.getTopicName(Topics.MANAGER_FIRE_EVENT_FINISHED), json);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    fireEventService.reduceFire(movingTeamEntity.getTeam(), movingTeamEntity.getFireEvent());
                    movingTeamEntity.setFireEnded(true);
                }
            }
        } else {
            if (movingTeamEntity.isFireEnded()) {
                System.out.println("Fire ended");
                movingTeamEntity.setDestination(movingTeamEntity.getFire_station().getCoords());
                movingTeamEntity.setRoute(routeService.getRoute(movingTeamEntity.getCurrent_position(), movingTeamEntity.getDestination()));
                movingTeamEntity.setStep(0);
                movingTeamEntity.setFireEnded(false);
                movingTeamEntity.setMoving(true);
                movingTeamEntity.setBackingHome(true);
            }
        }

    }
}
