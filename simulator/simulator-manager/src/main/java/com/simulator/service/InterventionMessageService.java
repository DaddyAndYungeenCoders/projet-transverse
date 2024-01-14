package com.simulator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.dto.InterventionMessageDTO;
import com.simulator.models.CoordsEntity;
import com.simulator.models.MovingTeamEntity;
import com.simulator.models.TeamEntity;

import java.util.List;


public class InterventionMessageService {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TeamService teamService = new TeamService();
    private static final FireStationService fireStationService = new FireStationService();
    private static InterventionMessageService interventionMessageService;
    private static MovingTeamService movingTeamService = new MovingTeamService();

    private InterventionMessageService() {
    }

    public static synchronized InterventionMessageService getInstance() {
        if (interventionMessageService == null) {
            interventionMessageService = new InterventionMessageService();
        }
        return interventionMessageService;
    }

    public static void OnIntervention(String message) {
        System.out.println("Intervention: " + message);
        try {
            InterventionMessageDTO interventionMessageDTO = objectMapper.readValue(message, InterventionMessageDTO.class);
            CoordsEntity fireCoords = interventionMessageDTO.getCoords();
            TeamEntity team = teamService.getTeamById(interventionMessageDTO.getTeam_id());
//            team.setFire_station(fireStationService.getFireStationById(interventionMessageDTO.getFire_station_id()));
            CoordsEntity fireStationCoords = team.getFire_station().getCoords();
            System.out.println("Fire station coords: " + fireStationCoords);
            MovingTeamEntity movingTeamEntity = new MovingTeamEntity(team, fireCoords, fireStationCoords, interventionMessageDTO.getStamina(),interventionMessageDTO.getFire_mastery_total());
            movingTeamService.addTeam(movingTeamEntity);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle or log the exception
        }
    }
}
