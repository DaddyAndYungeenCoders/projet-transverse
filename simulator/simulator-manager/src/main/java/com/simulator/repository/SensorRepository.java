package com.simulator.repository;

import com.simulator.models.CoordsEntity;
import com.simulator.models.SensorEntity;
import com.simulator.service.BddService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorRepository {
    private final BddService bddService = new BddService();

    public SensorEntity getSensorById(int id) {
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
}
