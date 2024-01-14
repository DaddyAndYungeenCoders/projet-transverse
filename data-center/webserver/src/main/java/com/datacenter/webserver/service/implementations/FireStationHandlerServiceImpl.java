package com.datacenter.webserver.service.implementations;

import com.datacenter.webserver.dto.BaseDTO;
import com.datacenter.webserver.dto.FireStationDTO;
import com.datacenter.webserver.models.FireStationEntity;
import com.datacenter.webserver.repository.FireEventRepository;
import com.datacenter.webserver.repository.FireStationRepository;
import com.datacenter.webserver.service.interfaces.AbstractOrchestrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FireStationHandlerServiceImpl extends AbstractOrchestrationService<FireStationEntity, FireStationDTO> {

    @Autowired
    public FireStationHandlerServiceImpl(FireStationRepository fireStationRepository) {
        this.repository = fireStationRepository;
    }
    
    @Override
    public Optional<FireStationEntity> create(FireStationDTO fireStationDTO) {
        return Optional.of(
                this.repository.save(
                        FireStationDTO.toEntity(fireStationDTO)
                ));
    }

    public Optional<FireStationEntity> createFromView(FireStationDTO fireStationDTO) {
        return this.create(fireStationDTO);
    }

    @Override
    public Optional<List<FireStationDTO>> getAll() {
        return Optional.of(repository.findAll().stream().map(FireStationEntity::toDTO).toList());
    }

    @Override
    public Optional<FireStationEntity> update(Long id, FireStationDTO fireStationDTO) {
        if (fireStationDTO == null || ObjectUtils.isEmpty(fireStationDTO) || !Objects.equals(fireStationDTO.getId(), id)) {
            return Optional.empty();
        }
        
        Optional<FireStationEntity> fireStationToUpdate = this.repository.findById(id);
        if (fireStationToUpdate.isEmpty()) {
            return Optional.empty();
        }
        fireStationToUpdate
                .map(fireStation -> FireStationDTO.toEntity(fireStationDTO));
        return Optional.of(this.repository.save(fireStationToUpdate.get()));
    }

    @Override
    public Optional<FireStationEntity> updateStatus(Long id, BaseDTO dto) {
        return Optional.empty();
    }
}
