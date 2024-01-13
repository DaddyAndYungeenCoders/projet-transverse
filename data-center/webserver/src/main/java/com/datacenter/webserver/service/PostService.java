package com.datacenter.webserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class PostService {
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendObject(String url, Object object) {
        try {
            String json = objectMapper.writeValueAsString(object);
            log.debug("Request JSON: {}", json);
            System.out.println("Request JSON: {}" + json);

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(json, requestHeaders);

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            String response = responseEntity.getBody();

            // Log the response
            log.debug("Server Response: {}", response);

            // Check if the response status is not in the 2xx range
            if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                log.error("Request failed with status code: {}", responseEntity.getStatusCodeValue());
                // Handle the error, throw an exception, or perform other error-specific actions
            }
        } catch (JsonProcessingException e) {
            log.error("Error processing JSON", e);
            // Handle the exception, throw an exception, or perform other error-specific actions
        }
    }
}
