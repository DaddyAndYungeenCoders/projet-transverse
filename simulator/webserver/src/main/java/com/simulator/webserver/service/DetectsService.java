package com.simulator.webserver.service;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.simulator.webserver.models.DetectsEntity;
import com.simulator.webserver.models.FireEventEntity;
import com.fasterxml.jackson.databind.ObjectMapper;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DetectsService {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private ObjectMapper objectMapper = new ObjectMapper();


    public DetectsService(){
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public String sendDetection(String url, DetectsEntity detectsEntity){
        try {
            String json = objectMapper.writeValueAsString(detectsEntity.toDTO());

            HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
            String response = restTemplate.exchange(url + "/new", HttpMethod.POST, requestEntity, String.class).getBody();
    
            // Affiche la réponse
            log.debug("Réponse du serveur : {}", response);
            return response;
        } catch (JsonProcessingException e) {
            // Gérer l'exception, par exemple en l'affichant
            e.printStackTrace();
            return "Erreur lors de la conversion en JSON";
        }

    }
}
