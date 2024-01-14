
package com.simulator.webserver.service.implementations;

import com.simulator.webserver.dto.TeamDTO;
import com.simulator.webserver.models.TeamEntity;
import com.simulator.webserver.repository.TeamRepository;
import com.simulator.webserver.service.interfaces.FireStationHandlerService;
import com.simulator.webserver.service.interfaces.TeamHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeamHandlerServiceImpl implements TeamHandlerService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FireStationHandlerService fireStationHandlerService;

    @Override
    public Optional<List<TeamEntity>> fetchAll() {
        return Optional.of(this.teamRepository.findAll());
    }

    @Override
    public Optional<TeamEntity> createTeam(TeamDTO teamDTO) {
        return Optional.of(this.teamRepository.save(teamDTO.toEntity()));
    }

    @Override
    public Optional<TeamEntity> update(Long id, TeamDTO teamDTO) {
        if (teamDTO == null || ObjectUtils.isEmpty(teamDTO) || !Objects.equals(teamDTO.getId(), id)) {
            return Optional.empty();
        }

        Optional<TeamEntity> teamEntityToUpdate = this.teamRepository.findById(id);
        if (teamEntityToUpdate.isEmpty()) {
            return Optional.empty();
        }
        teamEntityToUpdate
                .map(team -> teamDTO.toEntity());
        return Optional.of(this.teamRepository.save(teamEntityToUpdate.get()));
    }

    @Override
    public Optional<TeamEntity> getTeamById(Long id) {
        return this.teamRepository.findById(id);
    }
}