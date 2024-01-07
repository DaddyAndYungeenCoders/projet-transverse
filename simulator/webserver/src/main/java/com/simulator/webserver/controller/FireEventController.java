package com.simulator.webserver.controller;

import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.service.FireEventHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fire-event")
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

    // pour l'instant, create fire event from view est strictement identique a fromView, à voir si on le supprime ou
    // S'il est nécessaire de séparer les deux cas d'usage.
    @PostMapping("/create/generator")
    public ResponseEntity<FireEventEntity> createFireEventFromGenerator(@RequestBody FireEventDTO fireEvent) throws BadRequestException {
        System.out.println("[REST] - Request to create a fire " + fireEvent);
        if (!fireEvent.hasNecessaryValuesToCreate()) {
            throw new BadRequestException();
        } else {
            return this.fireEventHandlerService.createFireEvent(fireEvent)
                    .map(fire -> ResponseEntity.ok().body(fire))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<List<FireEventDTO>> getAllFires() {
        List<FireEventDTO> fireEventDTOList = new ArrayList<>();
        Optional<List<FireEventEntity>> response = fireEventHandlerService.getAllFireEvents();
        
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        response.get().forEach(fireEventEntity -> fireEventDTOList.add(fireEventEntity.toDTO()));
        
        return ResponseEntity.ok(fireEventDTOList);
    }
    
    @PutMapping("/update/{id}/{intensity}")
    public ResponseEntity<FireEventEntity> updateFireIntensityFromId(@PathVariable Long id, @PathVariable int intensity) {
        return this.fireEventHandlerService.updateFireEvent(id, intensity)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FireEventEntity> updateFireIntensityFromId(@PathVariable Long id, @RequestBody FireEventDTO fireEventDTO) {
        return this.fireEventHandlerService.updateFireEvent(id, fireEventDTO)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}