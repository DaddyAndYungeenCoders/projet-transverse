package com.simulator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class HttpService {
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    public String get(String url) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>("", requestHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String response = responseEntity.getBody();

        // Log the response

        // Check if the response status is not in the 2xx range
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("Request failed with status code: " + responseEntity.getStatusCodeValue());
            // Handle the error, throw an exception, or perform other error-specific actions
        }
        return response;
    }
}
