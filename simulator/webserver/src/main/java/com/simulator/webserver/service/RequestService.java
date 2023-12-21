package com.simulator.webserver.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.simulator.webserver.models.SensorEntity;

@Service
public class RequestService {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private ObjectMapper objectMapper = new ObjectMapper();

    public RequestService() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public String send(String url, String text) {
        String requestBody = "{\"text\":\"" + text + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        String response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();

        // Affiche la réponse
        System.out.println("Réponse du serveur : " + response);
        return response;
    }

    public String send_sensor(String url, String id, float intensity) {
        try {
            SensorEntity sensorEntity = new SensorEntity(id, intensity);
            String json = objectMapper.writeValueAsString(sensorEntity);
    
            HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
            String response = restTemplate.exchange(url + "/new", HttpMethod.POST, requestEntity, String.class).getBody();
    
            // Affiche la réponse
            System.out.println("Réponse du serveur : " + response);
            return response;
        } catch (JsonProcessingException e) {
            // Gérer l'exception, par exemple en l'affichant
            e.printStackTrace();
            return "Erreur lors de la conversion en JSON";
        }
    }
    
}