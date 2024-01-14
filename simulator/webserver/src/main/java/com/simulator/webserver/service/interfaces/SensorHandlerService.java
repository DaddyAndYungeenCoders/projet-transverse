package com.simulator.webserver.service.interfaces;

import com.simulator.webserver.dto.SensorDTO;
import com.simulator.webserver.models.SensorEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface SensorHandlerService {
    Optional<SensorEntity> createSensor(SensorDTO sensorDTO);
    Optional<List<SensorEntity>> getAllSensors();
    Optional<SensorEntity> updateSensor(Long id, SensorDTO sensorDTO);

    Optional<SensorEntity> updateSensorIntensity(Long id, int intensity);
}
