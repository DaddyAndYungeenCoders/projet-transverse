package com.simulator.repository;

import com.simulator.models.InterventionEntity;
import com.simulator.service.BddService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InterventionRepository {
    private static final BddService bddService = new BddService();

    public InterventionEntity getInterventionById(Long id) {
        ResultSet resultSet = bddService.Query("SELECT * FROM Intervention WHERE id = " + id);
        return buildInterventionEntity(resultSet);
    }

    public List<InterventionEntity> getAllInterventions() {
        ResultSet resultSet = bddService.Query("SELECT * FROM Intervention");
        return buildInterventionEntities(resultSet);
    }

    public static List<InterventionEntity> getInterventionsByTeamId(Long id) {
        ResultSet resultSet = bddService.Query("SELECT * FROM Intervention WHERE id_team = " + id);
        return buildInterventionEntities(resultSet);
    }

    public List<InterventionEntity> getInterventionsByFireEventId(Long id) {
        ResultSet resultSet = bddService.Query("SELECT * FROM Intervention WHERE id_fire_event = " + id);
        return buildInterventionEntities(resultSet);
    }

    public InterventionEntity addIntervention(InterventionEntity InterventionEntity) {
        bddService.Query("INSERT INTO Intervention (id_team, id_fire_event, duration) VALUES (?, ?, ?)",
                InterventionEntity.getTeam().getId(),
                InterventionEntity.getFire_event().getId(),
                InterventionEntity.getDuration());
        return InterventionEntity;
    }

    private static List<InterventionEntity> buildInterventionEntities(ResultSet resultSet) {
        List<InterventionEntity> InterventionEntities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                InterventionEntity InterventionEntity = new InterventionEntity();
                InterventionEntity.setTeam(new TeamRepository().getTeamById(resultSet.getLong("id_team")));
                InterventionEntity.setFire_event(new FireEventRepository().getFireEventById(resultSet.getLong("id_fire_event")));
                //TO-DO: en attente correction type BDD
//                InterventionEntity.setDuration(resultSet.getLong("duration"));
                InterventionEntities.add(InterventionEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return InterventionEntities;
    }

    private InterventionEntity buildInterventionEntity(ResultSet resultSet) {
        InterventionEntity InterventionEntity = new InterventionEntity();
        try {
            while (resultSet.next()) {
                InterventionEntity.setTeam(new TeamRepository().getTeamById(resultSet.getLong("id_team")));
                InterventionEntity.setFire_event(new FireEventRepository().getFireEventById(resultSet.getLong("id_fire_event")));
                InterventionEntity.setDuration(resultSet.getLong("duration"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return InterventionEntity;
    }
}
