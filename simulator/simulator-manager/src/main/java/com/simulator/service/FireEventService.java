package com.simulator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.simulator.config.AppConfig;
import com.simulator.dto.FireEventDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.models.FireEventEntity;
import com.simulator.models.SensorEntity;
import com.simulator.utils.Topics;

public class FireEventService {

    static ObjectMapper objectMapper = new ObjectMapper();
//    HTTPService httpService = new HTTPService();
    static SensorService sensorService = new SensorService();
    static PostService postService = new PostService();
    static MQTTService mqttService = new MQTTService();

    public FireEventService() {
    }

    public static void OnFireEvent(String message) {
        try {
            FireEventDTO fireEventDTO = objectMapper.readValue(message.toString(), FireEventDTO.class);
            FireEventEntity fireEvent = fireEventDTO.toEntity();
            System.out.println("fireEvent : " + fireEvent);
//                    List<SensorEntity> sensorEntities = httpService.getSensorEntities();
            String response = postService.GET(AppConfig.getWebServerURL() + "/api/sensor/fetch-all");
            System.out.println("Server Response : " + response);

            List<SensorEntity> sensorEntities = SensorService.convertJsonToSensorEntities(response);
            System.out.println(sensorEntities);
            SensorEntity nearestSensor = sensorService.findNearestSensor(sensorEntities, fireEvent.getCoords());
            nearestSensor.setIntensity(fireEvent.getReal_intensity());
            System.out.println("Nearest : " + nearestSensor.getId());
            String json = objectMapper.writeValueAsString(nearestSensor.toDTO());
            System.out.println("JSON sent to MQTT: " + json);
            mqttService.publish(Topics.SIMULATOR_NEW_SENSOR_VALUE, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle or log the exception
        }
    }
}
