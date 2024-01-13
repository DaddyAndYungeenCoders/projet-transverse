package com.simulator.webserver.service.interfaces;


import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.models.FireEventEntity;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface FireEventHandlerService {
    Optional<FireEventEntity> createFireEvent(FireEventDTO fireEventDTO);
    Optional<FireEventEntity> createFireEventFromView(FireEventDTO fireEventDTO);
    Optional<List<FireEventEntity>> getAllFireEvents();
    Optional<FireEventEntity> updateFireEvent(Long id, int intensity);
    Optional<FireEventEntity> updateFireEvent(Long id, FireEventDTO fireEventDTO);
    boolean isFireReal(Long id) throws BadRequestException;
}