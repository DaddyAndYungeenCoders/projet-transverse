package com.simulator.repository;

import com.simulator.models.HistoriqueEntity;
import com.simulator.service.BddService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueRepository {
    private final BddService bddService = new BddService();

    public HistoriqueEntity addHistorique(HistoriqueEntity historiqueEntity) {
        bddService.Query("INSERT INTO historique (id_user, id_fire_event, date_creation) VALUES (?, ?, ?)",
                historiqueEntity.getUser().getId(),
                historiqueEntity.getFire_event().getId(),
                historiqueEntity.getCreated_date());
        return historiqueEntity;
    }

    public HistoriqueEntity getHistoriqueByFireEventId(Long id) {
        ResultSet resultSet = bddService.Query("SELECT * FROM historique WHERE id_fire_event = " + id);
        return buildHistoriqueEntity(resultSet);
    }

    public List<HistoriqueEntity> getAllHistorique() {
        ResultSet resultSet = bddService.Query("SELECT * FROM historique");
        return buildHistoriqueEntities(resultSet);
    }

    private List<HistoriqueEntity> buildHistoriqueEntities(ResultSet resultSet) {
        List<HistoriqueEntity> historiqueEntities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                HistoriqueEntity historiqueEntity = new HistoriqueEntity();
                historiqueEntity.setUser(new UserRepository().getUserById(resultSet.getLong("id_user")));
                historiqueEntity.setFire_event(new FireEventRepository().getFireEventById(resultSet.getLong("id_fire_event")));
                historiqueEntity.setCreated_date(resultSet.getDate("date_creation"));
                historiqueEntities.add(historiqueEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historiqueEntities;
    }

    private HistoriqueEntity buildHistoriqueEntity(ResultSet resultSet) {
        HistoriqueEntity historiqueEntity = new HistoriqueEntity();
        try{
            while (resultSet.next()) {
                historiqueEntity.setUser(new UserRepository().getUserById(resultSet.getLong("id_user")));
                historiqueEntity.setFire_event(new FireEventRepository().getFireEventById(resultSet.getLong("id_fire_event")));
                historiqueEntity.setCreated_date(resultSet.getDate("date_creation"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return historiqueEntity;
    }
}
