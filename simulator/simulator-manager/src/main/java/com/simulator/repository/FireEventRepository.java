package com.simulator.repository;

import com.simulator.models.CoordsEntity;
import com.simulator.models.FireEventEntity;
import com.simulator.service.BddService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FireEventRepository {


    private final BddService bddService = new BddService();

    public FireEventEntity getFireEventById(Long id) {
        ResultSet resultSet = bddService.Query("SELECT * FROM fireevent WHERE id = " + id);
        return buildFireEventEntity(resultSet);
    }

    public List<FireEventEntity> getAllFireEvents() {
        ResultSet resultSet = bddService.Query("SELECT * FROM fireevent");
        return buildFireEventEntities(resultSet);
    }


    public List<FireEventEntity> getFireEventsByCoords(CoordsEntity coordsEntity) {
        ResultSet resultSet = bddService.Query("SELECT * FROM fireevent WHERE latitude = " + coordsEntity.getLatitude() + " AND longitude = " + coordsEntity.getLongitude());
        return buildFireEventEntities(resultSet);
    }

    public List<FireEventEntity> getFireEventsByDate(Date date) {
        ResultSet resultSet = bddService.Query("SELECT * FROM fireevent WHERE start_date = '" + date + "'");
        return buildFireEventEntities(resultSet);
    }

    public List<FireEventEntity> getFireEventsByCoordsAndDate(CoordsEntity coordsEntity, String date) {
        ResultSet resultSet = bddService.Query("SELECT * FROM fireevent WHERE latitude = " + coordsEntity.getLatitude() + " AND longitude = " + coordsEntity.getLongitude() + " AND start_date = '" + date + "'");
        return buildFireEventEntities(resultSet);
    }

    public FireEventEntity addFireEvent(FireEventEntity fireEventEntity) {
        bddService.Query("INSERT INTO fireevent (latitude, longitude, real_intensity, start_date, end_date, is_real) VALUES (?, ?, ?, ?, ?, ?)",
                fireEventEntity.getCoords().getLatitude(),
                fireEventEntity.getCoords().getLongitude(),
                fireEventEntity.getReal_intensity(),
                fireEventEntity.getStart_date(),
                fireEventEntity.getEnd_date(),
                fireEventEntity.isIs_real());
        return fireEventEntity;
    }


    private List<FireEventEntity> buildFireEventEntities(ResultSet resultSet) {
        List<FireEventEntity> fireEventEntities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                FireEventEntity fireEventEntity = new FireEventEntity();
                fireEventEntity.setId(resultSet.getLong("id"));
                CoordsEntity coordsEntity1 = new CoordsEntity();
                coordsEntity1.setLatitude(resultSet.getDouble("latitude"));
                coordsEntity1.setLongitude(resultSet.getDouble("longitude"));
                fireEventEntity.setCoords(coordsEntity1);
                fireEventEntity.setReal_intensity(resultSet.getInt("real_intensity"));
                fireEventEntity.setStart_date(resultSet.getDate("start_date"));
                fireEventEntity.setEnd_date(resultSet.getDate("end_date"));
                fireEventEntity.setIs_real(resultSet.getBoolean("is_real"));
                fireEventEntities.add(fireEventEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fireEventEntities;
    }

    private FireEventEntity buildFireEventEntity(ResultSet resultSet) {
        FireEventEntity fireEventEntity = new FireEventEntity();
        CoordsEntity coordsEntity = new CoordsEntity();
        try {
            while (resultSet.next()) {
                fireEventEntity.setId(resultSet.getLong("id"));
                coordsEntity.setLatitude(resultSet.getDouble("latitude"));
                coordsEntity.setLongitude(resultSet.getDouble("longitude"));
                fireEventEntity.setCoords(coordsEntity);
                fireEventEntity.setReal_intensity(resultSet.getInt("real_intensity"));
                fireEventEntity.setStart_date(resultSet.getDate("start_date"));
                fireEventEntity.setEnd_date(resultSet.getDate("end_date"));
                fireEventEntity.setIs_real(resultSet.getBoolean("is_real"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fireEventEntity;
    }

}
