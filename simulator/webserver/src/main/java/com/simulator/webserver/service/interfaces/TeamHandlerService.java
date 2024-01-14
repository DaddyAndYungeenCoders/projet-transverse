package com.simulator.webserver.service.interfaces;

import com.simulator.webserver.dto.TeamDTO;
import com.simulator.webserver.models.TeamEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface TeamHandlerService {
    Optional<List<TeamEntity>> fetchAll();

    Optional<TeamEntity> createTeam(@RequestBody TeamDTO teamDTO);

    Optional<TeamEntity> update(Long id, TeamDTO teamDTO);

    Optional<TeamEntity> getTeamById(Long id);
}
