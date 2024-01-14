package com.datacenter.webserver.service.interfaces;

import com.datacenter.webserver.dto.FireEventDTO;
import com.datacenter.webserver.dto.SensorDetectionDTO;
import com.datacenter.webserver.models.FireEventEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


public interface FireEventHandlerService {
    Optional<FireEventEntity> createFireEvent(@RequestBody FireEventDTO fireEventDTO);

    Optional<FireEventEntity> createFireEventFromView(@RequestBody FireEventDTO fireEventDTO);

    Optional<List<FireEventEntity>> getAllFireEvents();

    Optional<FireEventEntity> updateFireEvent(Long id, int intensity);

    Optional<FireEventEntity> updateFireEvent(Long id, FireEventDTO fireEventDTO);

    Optional<Boolean> isFireReal(Long id);
    Optional<FireEventEntity> updateFireEventValidationStatus(Long id, SensorDetectionDTO sensorDetectionDTO);
}
