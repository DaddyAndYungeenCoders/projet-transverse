package com.simulator.webserver.service;

import org.springframework.stereotype.Service;

import com.simulator.webserver.models.DetecsEntity;
import com.simulator.webserver.models.FireEventEntity;

@Service
public class DetecsService {

    public DetecsService(){

    }

    public String sendDetection(DetecsEntity detecsEntity){
        try {
            String json = objectMapper.writeValueAsString(detecsEntity.toDTO());

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
