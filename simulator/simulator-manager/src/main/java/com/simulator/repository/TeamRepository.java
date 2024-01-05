package com.simulator.repository;

import com.simulator.models.TeamEntity;
import com.simulator.service.BddService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamRepository {
    private final BddService bddService = new BddService();

    public TeamEntity getTeamById(Long id) {
        ResultSet resultSet = bddService.Query("SELECT * FROM team WHERE id = " + id);
        return buildTeamEntity(resultSet);
    }

    public TeamEntity getTeamByFireStationId(Long id) {
        ResultSet resultSet = bddService.Query("SELECT * FROM team WHERE fire_station_id = " + id);
        return buildTeamEntity(resultSet);
    }

    public List<TeamEntity> getAllTeams() {
        ResultSet resultSet = bddService.Query("SELECT * FROM team");
        return buildTeamEntities(resultSet);
    }

    public static List<TeamEntity> getTeamsByFireStationId(Long id) {
        ResultSet resultSet = new BddService().Query("SELECT * FROM team WHERE fire_station_id = " + id);
        return new TeamRepository().buildTeamEntities(resultSet);
    }

    private List<TeamEntity> buildTeamEntities(ResultSet resultSet) {
        List<TeamEntity> teamEntities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                TeamEntity teamEntity = new TeamEntity();
                teamEntity.setId(resultSet.getLong("id"));
                teamEntity.setFire_station(new FireStationRepository().getFireStationById(resultSet.getLong("fire_station_id")));
                teamEntity.setStamina(resultSet.getLong("stamina"));
                teamEntities.add(teamEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teamEntities;
    }

    private TeamEntity buildTeamEntity(ResultSet resultSet) {
        TeamEntity teamEntity = new TeamEntity();
        try {
            while (resultSet.next()) {
                teamEntity.setId(resultSet.getLong("id"));
                teamEntity.setFire_station(new FireStationRepository().getFireStationById(resultSet.getLong("fire_station_id")));
                teamEntity.setStamina(resultSet.getLong("stamina"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teamEntity;
    }
}
