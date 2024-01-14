package com.datacenter.webserver.service.implementations;

import com.datacenter.webserver.dto.BaseDTO;
import com.datacenter.webserver.dto.FireEventDTO;
import com.datacenter.webserver.dto.SensorDetectionDTO;
import com.datacenter.webserver.models.FireEventEntity;
import com.datacenter.webserver.models.ValidationStatus;
import com.datacenter.webserver.repository.FireEventRepository;
import com.datacenter.webserver.service.interfaces.AbstractOrchestrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FireEventHandlerServiceImpl extends AbstractOrchestrationService<FireEventEntity, FireEventDTO> {
    @Autowired
    public FireEventHandlerServiceImpl(FireEventRepository fireEventRepository) {
        this.repository = fireEventRepository;
    }
    
    @Override
    public Optional<FireEventEntity> create(FireEventDTO fireEventDTO) {
        return Optional.of(this.repository.save(fireEventDTO.toEntity()));
    }



    @Override
    public Optional<FireEventEntity> createFromView(FireEventDTO fireEventDTO) {
        return this.create(fireEventDTO); // TODO discuter de la pertinence
    }

    @Override
    public Optional<List<FireEventDTO>> getAll() {
        return Optional.of(this.repository.findAll().stream().map(FireEventEntity::toDTO).toList());
    }

    public Optional<FireEventEntity> updateFireEvent(Long id, int intensity) {
        Optional<FireEventEntity> fireToUpdate = this.repository.findById(id);
        if (fireToUpdate.isEmpty()) {
            return Optional.empty();
        }
        
        FireEventEntity fire = fireToUpdate.get();
        fire.setIntensity(intensity);
        return Optional.of(this.repository.save(fire));
    }

    @Override
    public Optional<FireEventEntity> update(Long id, FireEventDTO fireEventDTO) {
        Optional<FireEventEntity> fireToUpdate = this.repository.findById(id);
        if (fireToUpdate.isEmpty()) {
            return Optional.empty();
        }
        FireEventEntity fire = fireToUpdate.get();
        updateFireEventData(fireEventDTO, fire);
        return Optional.of(this.repository.save(fire));
    }

    @Override
    public Optional<FireEventEntity> updateStatus(Long id, BaseDTO dto) {
        // get sur historique et set a verified

        return Optional.empty();
    }

    //TODO Mapper with MapStruct instead of this function
    private void updateFireEventData(FireEventDTO fireEventDTO, FireEventEntity fireEventEntity) {
        fireEventEntity.setIntensity(fireEventDTO.getIntensity());
        fireEventEntity.set_verified(fireEventDTO.isVerified());
        fireEventEntity.setStart_date(fireEventDTO.getStartDate());
        fireEventEntity.setCoords(fireEventDTO.getCoords());
    }

}