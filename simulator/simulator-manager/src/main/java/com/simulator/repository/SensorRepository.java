package com.simulator.repository;

import com.simulator.models.CoordsEntity;
import com.simulator.models.SensorEntity;
import com.simulator.service.BddService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SensorRepository {
    private static final BddService bddService = new BddService();


    public static SensorEntity getSensorById(Long id) {
        ResultSet resultSet = bddService.Query("SELECT * FROM sensor WHERE id = " + id);
        SensorEntity sensorEntity = new SensorEntity();
        try {
            while (resultSet.next()) {
                sensorEntity.setId(resultSet.getLong("id"));
                CoordsEntity coordsEntity = new CoordsEntity();
                coordsEntity.setLatitude(resultSet.getDouble("latitude"));
                coordsEntity.setLongitude(resultSet.getDouble("longitude"));
                sensorEntity.setCoordsEntity(coordsEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return sensorEntity;
    }

    public List<SensorEntity> getAllSensors() {
        ResultSet resultSet = bddService.Query("SELECT * FROM sensor");
        List<SensorEntity> sensorEntities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                SensorEntity sensorEntity = new SensorEntity();
                sensorEntity.setId(resultSet.getLong("id"));
                CoordsEntity coordsEntity = new CoordsEntity();
                coordsEntity.setLatitude(resultSet.getDouble("latitude"));
                coordsEntity.setLongitude(resultSet.getDouble("longitude"));
                sensorEntity.setCoordsEntity(coordsEntity);
                sensorEntities.add(sensorEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return sensorEntities;
    }
}
