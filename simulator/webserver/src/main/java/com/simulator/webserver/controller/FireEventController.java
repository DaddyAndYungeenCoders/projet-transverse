package com.simulator.webserver.controller;

import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.service.interfaces.FireEventHandlerService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class FireEventController {

    private FireEventHandlerService fireEventHandlerService;

    @PostMapping("/create")
    public ResponseEntity<FireEventEntity> createFireEventFromView(@RequestBody FireEventDTO fireEvent) throws BadRequestException {
        if (!fireEvent.hasNecessaryValuesToCreate()) {
            throw new BadRequestException();
        } else {
            return this.fireEventHandlerService.createFireEventFromView(fireEvent)
                    .map(fire -> ResponseEntity.ok().body(fire))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
    }
}
