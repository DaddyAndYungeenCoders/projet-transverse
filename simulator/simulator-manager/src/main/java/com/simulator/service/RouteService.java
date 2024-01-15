package com.simulator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.models.CoordsEntity;
import com.simulator.models.RouteResponseEntity;

public class RouteService {

    private static final HttpService httpService = new HttpService();

    String baseUrl = "https://routing.openstreetmap.de/routed-car/route/v1/driving/";
    String params = "?overview=false&alternatives=false&steps=true";

    public RouteResponseEntity getRoute(CoordsEntity depart, CoordsEntity arrivee) {
        String url = baseUrl + depart.getLongitude() + "," + depart.getLatitude() + ";" + arrivee.getLongitude() + "," + arrivee.getLatitude() + params;
        String response = httpService.get(url);
        System.out.println(response);
        return convertJsonToRouteResponseEntity(response);
    }

    private static RouteResponseEntity convertJsonToRouteResponseEntity(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response, RouteResponseEntity.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
