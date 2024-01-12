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

public class FireEventService {

    ObjectMapper objectMapper = new ObjectMapper();
//    HTTPService httpService = new HTTPService();
    SensorService sensorService = new SensorService();
    PostService postService = new PostService();

    public FireEventService() {
        start();
    }

    private void start() {
        MQTTService mqttService = new MQTTService();
        List<String> topicsToSubscribe = List.of("simulator-view.fire-event");
        mqttService.subscribeToTopics(topicsToSubscribe, (topic, message) -> {
            if (Objects.equals(topic, mqttService.getTopic("simulator-view.fire-event"))) {
                System.out.println("Received message on topic " + topic + ": " + new String(String.valueOf(message)));
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
                    String json = objectMapper.writeValueAsString(nearestSensor);
                    mqttService.publish("simulator.new-sensor-value", json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace(); // Handle or log the exception
                }

            } else {
                System.out.println("Received message on topic inconnu" + topic + ": " + new String(message.getPayload()));

            }
        });
    }
}
