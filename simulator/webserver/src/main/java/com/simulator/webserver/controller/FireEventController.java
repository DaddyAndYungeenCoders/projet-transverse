package com.simulator.webserver.controller;

import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.service.interfaces.FireEventHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FireEventController {

    private final FireEventHandlerService fireEventHandlerService;

    @PostMapping("/create")
    public ResponseEntity<FireEventEntity> createFireEventFromView(@RequestBody FireEventDTO fireEvent) throws BadRequestException {
        System.out.println("[REST] - Request to create a fire " + fireEvent);
        if (!fireEvent.hasNecessaryValuesToCreate()) {
            throw new BadRequestException();
        } else {
            return this.fireEventHandlerService.createFireEventFromView(fireEvent)
                    .map(fire -> ResponseEntity.ok().body(fire))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
    }
}
