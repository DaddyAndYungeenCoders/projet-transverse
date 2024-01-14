package com.datacenter.webserver.service.implementations;

import com.datacenter.webserver.dto.SensorDTO;
import com.datacenter.webserver.dto.TeamDTO;
import com.datacenter.webserver.models.Coords;
import com.datacenter.webserver.models.TeamEntity;
import com.datacenter.webserver.repository.TeamRepository;
import com.datacenter.webserver.service.interfaces.AbstractOrchestrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamHandlerServiceImpl extends AbstractOrchestrationService<TeamEntity, TeamDTO> {
    
    @Autowired
    public TeamHandlerServiceImpl(TeamRepository teamRepository) {
        this.repository = teamRepository;
    }
    
    @Override
    public Optional<TeamEntity> create(TeamDTO dto) {
        return Optional.of(
                this.repository.save(
                        TeamDTO.toEntity(dto)
                ));
    }

    @Override
    public Optional<TeamEntity> createFromView(TeamDTO dto) {
        return Optional.empty();
    }

    @Override
    public Optional<List<TeamDTO>> getAll() {
        return Optional.of(this.repository.findAll().stream().map(TeamEntity::toDTO).toList());
    }

    @Override
    public Optional<TeamEntity> update(Long id, TeamDTO dto) {
        return Optional.empty();
    }

    @Override
    public Optional<TeamEntity> updateVerificationStatus(Long id, boolean isVerified) {
        return Optional.empty();
    }

    public Optional<TeamEntity> updateCoords(Long id, Coords coords) {
        return this.repository.findById(id)
                .map(teamEntity -> {
                    teamEntity.setCurrent_longitude(coords.getLongitude());
                    teamEntity.setCurrent_latitude(coords.getLatitude());
                    return this.repository.save(teamEntity);
                });
    }

    public Optional<TeamEntity> updateIsAvailable(Long id, boolean isAvailable) {
        return this.repository.findById(id)
                .map(teamEntity -> {
                    teamEntity.set_available(isAvailable);
                    return this.repository.save(teamEntity);
                });
    }

    @Override
    public Optional<TeamEntity> getFromId(Long id) {
        return Optional.empty();
    }
}
