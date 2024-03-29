package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.utils.LoggerUtil;
import org.slf4j.Logger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class HttpService {
    private static final Logger logger = LoggerUtil.getLogger();
    private final static RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public static String get(String url) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>("", requestHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String response = responseEntity.getBody();

        // Check if the response status is not in the 2xx range
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            logger.warn("Request failed with status code: " + responseEntity.getStatusCode());
        }
        return response;
    }
}
