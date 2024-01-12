package com.simulator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.simulator.dto.FireEventDTO;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.models.FireEventEntity;
import com.simulator.models.SensorEntity;

public class FireEventService {

    ObjectMapper objectMapper = new ObjectMapper();
    HTTPService httpService = new HTTPService();
    SensorService sensorService = new SensorService();

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
                    List<SensorEntity> sensorEntities = httpService.getSensorEntities();

                    SensorEntity nearestSensor = sensorService.findNearestSensor(sensorEntities, fireEvent.getCoords());
                    nearestSensor.setIntensity(fireEvent.getReal_intensity());
                    String json = objectMapper.writeValueAsString(nearestSensor);
                    mqttService.publish("simulator-view.sensor-changed", json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace(); // Handle or log the exception
                }

            } else {
                System.out.println("Received message on topic inconnu" + topic + ": " + new String(message.getPayload()));

            }
        });
    }
}
