package com.simulator.webserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.service.PostService;
import com.simulator.webserver.service.interfaces.FireEventHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        System.out.println("[REST] - LES COORDS " + fireEvent.getCoords());

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
        ResponseEntity<FireEventEntity> result = this.fireEventHandlerService.updateFireEvent(id, fireEventDTO)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());

        this.notifyFrontEnd();

        return result;
    }

    @PutMapping("/update-handled/{id}")
    public ResponseEntity<FireEventEntity> updateIsHandled(@PathVariable Long id) throws Exception {
        ResponseEntity<FireEventEntity> result = this.fireEventHandlerService.updateIsHandled(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

        this.notifyFrontEnd();
        return result;
    };

    @GetMapping("/is-real/{sensorId}")
    public ResponseEntity<Boolean> isThereAtLeastOneRealFireInDetectionZone(@PathVariable Long sensorId) {
        boolean isThereARealFire = fireEventHandlerService.isThereARealFireNearSensor(sensorId);
        System.out.println("isThereARealFireInDetectionZone: " + isThereARealFire);
        return new ResponseEntity<>(isThereARealFire, HttpStatus.OK);
    }

    @GetMapping("/getBySensorId/{sensorId}")
    public ResponseEntity<FireEventEntity> getFireEventBySensorId(@PathVariable Long sensorId) {
        return this.fireEventHandlerService.getFireEventBySensorId(sensorId)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFireEvent(@PathVariable Long id) {
        try {
            this.fireEventHandlerService.deleteFireEvent(id);
            System.out.println("bonjour");
            this.notifyFrontEnd();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error deleting fire event with id: {}", id, e);
            this.notifyFrontEnd();
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    String getEntityTopic() {
        return "fire-event";
    }
}