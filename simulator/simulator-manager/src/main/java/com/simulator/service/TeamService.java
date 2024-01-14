package com.simulator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.config.AppConfig;
import com.simulator.models.FireStationEntity;
import com.simulator.models.TeamEntity;

import java.io.IOException;

public class TeamService {
    private static final String BASE_URL = AppConfig.getWebServerURL() + "/api/team";

    public TeamEntity getTeamById(Long teamId) {
        String response = HttpService.get(BASE_URL + "/get/" + teamId);
        return TeamService.convertJsonToTeamEntity(response);
    }

    private static TeamEntity convertJsonToTeamEntity(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response, TeamEntity.class);
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to TeamEntities", e);
        }
    }
}
