//package com.simulator.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.simulator.config.AppConfig;
//import com.simulator.models.SensorEntity;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.ClientResponse;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HTTPService {
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final ObjectMapper objectMapper = new ObjectMapper();
//    private final WebClient webClient = WebClient.create();
//
//    private String url = AppConfig.getWebServerURL();
//
//    public List<SensorEntity> getSensorEntities() {
//        WebClient.ResponseSpec request = webClient.get()
//                .uri(url + "/api/sensor/fetch-all")
//                .retrieve();
//
//        ResponseEntity<Void> clientResponse = request.toBodilessEntity().block();
//        HttpStatusCode statusCode = clientResponse.getStatusCode();
//
//        String response = clientResponse.getBody().toString();
//
//        // Log the response
//        System.out.println("Server Response: " + response);
//
//        // Check if the response status is not in the 2xx range
//        if (statusCode.isError()) {
//            System.out.println("Request failed with status code: " + statusCode);
//            // Handle the error, throw an exception, or perform other error-specific actions
//        }
//
//        List<SensorEntity> sensorEntities;
//        try {
//            sensorEntities = objectMapper.readValue(response, new TypeReference<List<SensorEntity>>() {});
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("Error parsing JSON response", e);
//        }
//
//        sensorEntities.forEach(sensor -> {
//            System.out.println("Sensor id: " + sensor.getId());
//        });
//
//        return sensorEntities;
//    }
//}
