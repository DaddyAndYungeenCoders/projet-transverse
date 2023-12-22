package com.simulator.webserver.service.interfaces;

import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.models.FireEventEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface FireEventHandlerService {
    Optional<FireEventEntity> createFireEventFromView(@RequestBody FireEventDTO fireEventDTO);
}
