package com.simulator.webserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.service.PostService;
import com.simulator.webserver.service.implementations.SensorHandlerServiceImpl;
import com.simulator.webserver.service.interfaces.FireEventHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fire-event")
public class FireEventController extends AbstractController<FireEventEntity, FireEventDTO> {

    private final FireEventHandlerService fireEventHandlerService;

    private PostService postService = new PostService();
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private ObjectMapper objectMapper = new ObjectMapper();


    @Value("${relay.url}")
    private String relayURL;
    @Value("${passerelle.url}")
    private String passerelleURL;
    @Override
    @PostMapping("/create")
    public ResponseEntity<FireEventEntity> create(@RequestBody FireEventDTO fireEvent) throws BadRequestException {
        System.out.println("[REST] - Request to create a fire " + fireEvent);
        if (!fireEvent.hasNecessaryValuesToCreate()) {
            throw new BadRequestException();
        } else {
            ResponseEntity<FireEventEntity> result = this.fireEventHandlerService.createFireEventFromView(fireEvent)
                    .map(fire -> ResponseEntity.ok().body(fire))
                    .orElseGet(() -> ResponseEntity.notFound().build());

            postService.sendObject(relayURL, Objects.requireNonNull(result.getBody()).toDTO());
            this.notifyFrontEnd();
            
            return result;
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
            ResponseEntity<FireEventEntity> result = this.fireEventHandlerService.createFireEvent(fireEvent)
                    .map(fire -> ResponseEntity.ok().body(fire))
                    .orElseGet(() -> ResponseEntity.notFound().build());
            
            this.notifyFrontEnd();

            return result;
        }
    }

    @Override
    @GetMapping("/fetch-all")
    public ResponseEntity<List<FireEventDTO>> fetchAll() {
        List<FireEventDTO> fireEventDTOList = fireEventHandlerService.getAllFireEvents()
                .map(fireEventEntities -> fireEventEntities.stream()
                        .map(FireEventEntity::toDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        return ResponseEntity.ok(fireEventDTOList);
    }
    
    @GetMapping("/is-real/{id}")
    public ResponseEntity<Boolean> isFireEventReal(@PathVariable Long id) {
        return fireEventHandlerService.isFireReal(id)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/update/{id}/{intensity}")
    public ResponseEntity<FireEventEntity> updateFireIntensityFromId(@PathVariable Long id, @PathVariable int intensity) {
        ResponseEntity<FireEventEntity> result = this.fireEventHandlerService.updateFireEvent(id, intensity)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());
        
        this.notifyFrontEnd();
        
        return result;
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<FireEventEntity> update(@PathVariable Long id, @RequestBody FireEventDTO fireEventDTO) {
        ResponseEntity<FireEventEntity> result =  this.fireEventHandlerService.updateFireEvent(id, fireEventDTO)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());

        this.notifyFrontEnd();

        return result;
    }

    @Override
    String getEntityTopic() {
        return "fire-event";
    }
}