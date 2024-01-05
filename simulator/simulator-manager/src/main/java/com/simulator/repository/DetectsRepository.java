package com.simulator.repository;

import com.simulator.models.DetectsEntity;
import com.simulator.service.BddService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetectsRepository {

    private final BddService bddService = new BddService();

    public List<DetectsEntity> getDetectsBySensorId(Long id) {
        ResultSet resultSet = bddService.Query("SELECT * FROM detects WHERE id_sensor = " + id);
        return buildDetectsEntities(resultSet);
    }

    public DetectsEntity getDetectsByFireEventId(Long id) {
        ResultSet resultSet = bddService.Query("SELECT * FROM detects WHERE id_fire_event = " + id);
        return buildDetectsEntity(resultSet);
    }

    public List<DetectsEntity> getAllDetects() {
        ResultSet resultSet = bddService.Query("SELECT * FROM detects");
        return buildDetectsEntities(resultSet);
    }

    public DetectsEntity addDetects(DetectsEntity detectsEntity) {
        String query = "INSERT INTO detects (id_sensor, id_fire_event, intensity) VALUES (?, ?, ?)";
        try {
            bddService.Query(query, detectsEntity.getSensorEntity().getId(), detectsEntity.getFireEventEntity().getId(), detectsEntity.getIntensity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detectsEntity;
    }

    private DetectsEntity buildDetectsEntity(ResultSet resultSet) {
        DetectsEntity detectsEntity = new DetectsEntity();
        try{
            while (resultSet.next()) {
                detectsEntity.setSensorEntity(new SensorRepository().getSensorById(resultSet.getLong("id_sensor")));
                detectsEntity.setFireEventEntity(new FireEventRepository().getFireEventById(resultSet.getLong("id_fire_event")));
                detectsEntity.setIntensity(resultSet.getFloat("intensity"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return detectsEntity;
    }

    private List<DetectsEntity> buildDetectsEntities(ResultSet resultSet) {
        List<DetectsEntity> detectsEntities = new ArrayList<>();
        try{
            while (resultSet.next()) {
                DetectsEntity detectsEntity = new DetectsEntity();
                detectsEntity.setSensorEntity(new SensorRepository().getSensorById(resultSet.getLong("id_sensor")));
                detectsEntity.setFireEventEntity(new FireEventRepository().getFireEventById(resultSet.getLong("id_fire_event")));
                detectsEntity.setIntensity(resultSet.getFloat("intensity"));
                detectsEntities.add(detectsEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return detectsEntities;
    }
}
