package com.simulator.repository;

import com.simulator.models.CoordsEntity;
import com.simulator.models.FireStationEntity;
import com.simulator.service.BddService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FireStationRepository {
    private final BddService bddService = new BddService();

    public FireStationEntity getFireStationById(Long id) {
        ResultSet resultSet = bddService.Query("SELECT * FROM firestation WHERE id = " + id);
        return buildFireStationEntity(resultSet);
    }

    public FireStationEntity getFireStationByCoords(CoordsEntity coordsEntity) {
        ResultSet resultSet = bddService.Query("SELECT * FROM firestation WHERE latitude = " + coordsEntity.getLatitude() + " AND longitude = " + coordsEntity.getLongitude());
        return buildFireStationEntity(resultSet);
    }
    public List<FireStationEntity> getAllFireStations() {
        ResultSet resultSet = bddService.Query("SELECT * FROM firestation");
        return buildFireStationEntities(resultSet);
    }

    private List<FireStationEntity> buildFireStationEntities(ResultSet resultSet) {
        List<FireStationEntity> fireStationEntities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                FireStationEntity fireStationEntity = new FireStationEntity();
                CoordsEntity coordsEntity = new CoordsEntity();
                fireStationEntity.setId(resultSet.getLong("id"));
                fireStationEntity.setName(resultSet.getString("name"));
                fireStationEntity.setAddress(resultSet.getString("address"));
                coordsEntity.setLatitude(resultSet.getFloat("latitude"));
                coordsEntity.setLongitude(resultSet.getFloat("longitude"));
                fireStationEntity.setCoords(coordsEntity);
                fireStationEntities.add(fireStationEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fireStationEntities;
    }

    private FireStationEntity buildFireStationEntity(ResultSet resultSet) {
        FireStationEntity fireStationEntity = new FireStationEntity();
        CoordsEntity coordsEntity = new CoordsEntity();
        try {
            while (resultSet.next()) {
                fireStationEntity.setId(resultSet.getLong("id"));
                fireStationEntity.setName(resultSet.getString("name"));
                fireStationEntity.setAddress(resultSet.getString("address"));
                coordsEntity.setLatitude(resultSet.getFloat("latitude"));
                coordsEntity.setLongitude(resultSet.getFloat("longitude"));
                fireStationEntity.setCoords(coordsEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fireStationEntity;
    }
}
