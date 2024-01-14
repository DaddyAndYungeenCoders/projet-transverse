package com.simulator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.config.AppConfig;
import com.simulator.models.FireStationEntity;

import java.io.IOException;

public class FireStationService {

    private static final String BASE_URL = AppConfig.getWebServerURL() + "/api/fire-station";

    public FireStationEntity getFireStationById(Long fireStationId) {
        String response = HttpService.get(BASE_URL + "/get/" + fireStationId);
        return FireStationService.convertJsonToFireStationEntity(response);
    }

    private static FireStationEntity convertJsonToFireStationEntity(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response, FireStationEntity.class);
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to FireStationEntities", e);
        }
    }
}
