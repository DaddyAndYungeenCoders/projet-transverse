package com.simulator.service;

import com.simulator.models.CoordsEntity;
import com.simulator.models.SensorEntity;

import java.util.List;

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

    // A helper method to calculate the distance between two sets of coordinates
    private double calculateDistance(CoordsEntity coords1, CoordsEntity coords2) {
        double lat1 = coords1.getLatitude();
        double lon1 = coords1.getLongitude();
        double lat2 = coords2.getLatitude();
        double lon2 = coords2.getLongitude();

        // Use a suitable formula (e.g., Haversine formula) to calculate the distance
        // This is a simplified formula for illustration purposes, you might need a more accurate one
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));
    }

}
