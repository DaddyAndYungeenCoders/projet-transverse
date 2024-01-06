package com.simulator.webserver.service;


import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.models.FireEventEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface FireEventHandlerService {
    Optional<FireEventEntity> createFireEvent(@RequestBody FireEventDTO fireEventDTO);
    Optional<FireEventEntity> createFireEventFromView(@RequestBody FireEventDTO fireEventDTO);
    Optional<List<FireEventEntity>> getAllFireEvents();
    Optional<FireEventEntity> updateFireEvent(Long id, int intensity);
}