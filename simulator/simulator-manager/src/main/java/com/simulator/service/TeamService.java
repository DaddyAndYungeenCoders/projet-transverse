package com.simulator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.config.AppConfig;
import com.simulator.dto.TeamDTO;
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
            System.out.println(response);
            return objectMapper.readValue(response, TeamDTO.class).toEntity();
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
