package com.simulator.webserver.service.implementations;

import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.repository.FireEventRepository;
import com.simulator.webserver.service.interfaces.FireEventHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FireEventHandlerServiceImpl implements FireEventHandlerService {
    @Autowired
    private FireEventRepository fireEventRepository;


    @Override
    public Optional<FireEventEntity> createFireEvent(FireEventDTO fireEventDTO) {
        FireEventEntity entity = new FireEventEntity();
        entity.set_real(fireEventDTO.isReal());
        entity.setCoords(fireEventDTO.getCoords());
        entity.setEnd_date(null);
        entity.setStart_date(fireEventDTO.getStartDate());
        entity.setReal_intensity(fireEventDTO.getRealIntensity());
        return Optional.of(fireEventRepository.save(entity));
    }



    @Override
    public Optional<FireEventEntity> createFireEventFromView(FireEventDTO fireEventDTO) {
        return this.createFireEvent(fireEventDTO); // TODO discuter de la pertinence
    }

    @Override
    public Optional<List<FireEventEntity>> getAllFireEvents() {
        return Optional.of(fireEventRepository.findAll());
    }

    @Override
    public Optional<FireEventEntity> updateFireEvent(Long id, int intensity) {
        Optional<FireEventEntity> fireToUpdate = this.fireEventRepository.findById(id);
        if (fireToUpdate.isEmpty()) {
            return Optional.empty();
        }
        
        FireEventEntity fire = fireToUpdate.get();
        fire.setReal_intensity(intensity);
        return Optional.of(this.fireEventRepository.save(fire));
    }

    @Override
    public Optional<FireEventEntity> updateFireEvent(Long id, FireEventDTO fireEventDTO) {
        Optional<FireEventEntity> fireToUpdate = this.fireEventRepository.findById(id);
        if (fireToUpdate.isEmpty()) {
            return Optional.empty();
        }
        FireEventEntity fire = fireToUpdate.get();
//        updateFireEventData(fireEventDTO, fire);
        return Optional.of(this.fireEventRepository.save(fireEventDTO.toEntity()));
    }

    @Override
    public boolean isThereARealFireNearSensor(Long id) {
        Optional<List<FireEventEntity>> fireEventEntity = this.fireEventRepository.findBySensorId(id);

        List<FireEventEntity> filteredList = fireEventEntity
                .map(list -> list.stream()
                        .filter(entity -> entity.getReal_intensity() > 0 && entity.is_real())
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        System.out.println("isThereARealFireNearSensor: " + filteredList.toString());
        return !filteredList.isEmpty();
    }

    public void deleteFireEvent(Long id) {
        fireEventRepository.deleteById(id);
    }

    @Override
    public Optional<FireEventEntity> updateIsHandled(Long id) {
        Optional<FireEventEntity> fireToUpdate = this.fireEventRepository.findById(id);
        if (fireToUpdate.isEmpty()) {
            return Optional.empty();
        }
        FireEventEntity fire = fireToUpdate.get();
        fire.set_handled(true);
        return Optional.of(this.fireEventRepository.save(fire));
    }

    @Override
    public Optional<FireEventEntity> getFireEventBySensorId(Long sensorId) {
        Optional<List<FireEventEntity>> fireEventEntity = this.fireEventRepository.findBySensorId(sensorId);
        return fireEventEntity.map(list -> list.stream()
                .filter(entity -> entity.getReal_intensity() > 0 && entity.is_real())
                .findFirst())
                .orElse(Optional.empty());
    }

    @Override
    public boolean isFireReal(Long id) throws BadRequestException {
        return false;
    }


    //TODO Mapper with MapStruct instead of this function
    private void updateFireEventData(FireEventDTO fireEventDTO, FireEventEntity fireEventEntity) {
        fireEventEntity.setReal_intensity(fireEventDTO.getRealIntensity());
        fireEventEntity.set_real(fireEventDTO.isReal());
        fireEventEntity.setStart_date(fireEventDTO.getStartDate());
        fireEventEntity.setCoords(fireEventDTO.getCoords());
    }
}