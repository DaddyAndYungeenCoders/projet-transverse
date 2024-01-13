package com.simulator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.dto.SensorDTO;
import com.simulator.models.CoordsEntity;
import com.simulator.models.SensorEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SensorService {
    public SensorEntity findNearestSensor(List<SensorEntity> allSensors, CoordsEntity coords) {

        SensorEntity nearestSensor = null;
        double minDistance = Double.MAX_VALUE;

        for (SensorEntity sensor : allSensors) {
            double distance = calculateDistance(sensor.getCoordsEntity(), coords);
            if (distance < minDistance) {
                minDistance = distance;
                nearestSensor = sensor;
            }
        }

        return nearestSensor;
    }

    private double calculateDistance(CoordsEntity coords1, CoordsEntity coords2) {
        double lat1 = coords1.getLatitude();
        double lon1 = coords1.getLongitude();
        double lat2 = coords2.getLatitude();
        double lon2 = coords2.getLongitude();

        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));
    }

    public static List<SensorEntity> convertJsonToSensorEntities(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<SensorDTO> sensorDTOList = objectMapper.readValue(
                    jsonString,
                    new TypeReference<List<SensorDTO>>() {}
            );

            return sensorDTOList.stream()
                    .map(SensorDTO::toEntity)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to SensorEntities", e);
        }
    }
}
