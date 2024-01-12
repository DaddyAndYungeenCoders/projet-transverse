package com.simulator.webserver.service.implementations;

import com.simulator.webserver.dto.SensorDTO;
import com.simulator.webserver.models.SensorEntity;
import com.simulator.webserver.repository.SensorRepository;
import com.simulator.webserver.service.interfaces.SensorHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SensorHandlerServiceImpl implements SensorHandlerService {
    @Autowired
    private SensorRepository sensorRepository;
    
    @Override
    public Optional<SensorEntity> createSensor(SensorDTO sensorDTO) {
        return Optional.of(
                this.sensorRepository.save(
                        SensorDTO.toEntity(sensorDTO)
                ));
    }

    @Override
    public Optional<List<SensorEntity>> getAllSensors() {
        return Optional.of(this.sensorRepository.findAll());
    }

    @Override
    public Optional<SensorEntity> updateSensor(Long id, SensorDTO sensorDTO) {
        if (sensorDTO == null || ObjectUtils.isEmpty(sensorDTO) || !Objects.equals(sensorDTO.getId(), id)) {
            return Optional.empty();
        }

        Optional<SensorEntity> sensorToUpdate = this.sensorRepository.findById(id);
        if (sensorToUpdate.isEmpty()) {
            return Optional.empty();
        }
        sensorToUpdate
                .map(sensorEntity -> SensorDTO.toEntity(sensorDTO));
        return Optional.of(this.sensorRepository.save(sensorToUpdate.get()));    }
}
