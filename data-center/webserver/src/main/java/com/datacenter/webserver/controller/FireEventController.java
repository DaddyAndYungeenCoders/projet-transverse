package com.datacenter.webserver.controller;

import com.datacenter.webserver.dto.FireEventDTO;
import com.datacenter.webserver.dto.SensorDetectionVM;
import com.datacenter.webserver.models.Coords;
import com.datacenter.webserver.models.FireEventEntity;
import com.datacenter.webserver.models.SensorEntity;
import com.datacenter.webserver.service.implementations.FireEventHandlerServiceImpl;
import com.datacenter.webserver.service.implementations.SensorHandlerServiceImpl;
import com.datacenter.webserver.service.implementations.WebSocketService;
import com.datacenter.webserver.service.interfaces.FireEventHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/fire-event")
public class FireEventController extends CRUDController<FireEventEntity, FireEventDTO> {
    private final SensorHandlerServiceImpl sensorService;
    private final FireEventHandlerServiceImpl fireEventService;

    @Autowired
    public FireEventController(FireEventHandlerServiceImpl fireEventService,
                               WebSocketService webSocketService, SensorHandlerServiceImpl sensorService) {
        super(fireEventService);
        this.webSocketService = webSocketService;
        this.sensorService = sensorService;
        this.fireEventService = fireEventService;
    }
    
    @Override
    String getEntityTopic() {
        return "fire-event";
    }

    @Override
    public ResponseEntity<FireEventEntity> create(@RequestBody SensorDetectionVM sensorDetectionVM) throws Exception {
        Optional<SensorEntity> sensorEntity = this.sensorService.getFromId(sensorDetectionVM.getId());

        if (sensorEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Coords sensorCoordinates = this.sensorService.getFromId(sensorDetectionVM.getId())
                .map(SensorEntity::getCoords)
                .orElse(null);

        if (sensorCoordinates == null) {
            return ResponseEntity.notFound().build();
        }

        FireEventDTO fireEventDTO = new FireEventDTO();
        fireEventDTO.setSensorId(sensorDetectionVM.getId());
        fireEventDTO.setIntensity(sensorDetectionVM.getIntensity());
        java.util.Date utilDate = java.util.Date.from(Instant.now());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        fireEventDTO.setStartDate(sqlDate);
        fireEventDTO.setEndDate(null);
        fireEventDTO.setVerified(false);
        fireEventDTO.setHandled(false);
        fireEventDTO.setIdEquipeIntervention(null); // WTF ?

        fireEventDTO.setCoords(sensorCoordinates);
        this.notifyFrontEnd();

        return this.create(fireEventDTO);
    }

    @PutMapping("/updateIsVerified/{id}")
    public ResponseEntity<FireEventEntity> updateIsVerified(@PathVariable Long id, @RequestBody FireEventDTO dto) throws Exception {
        ResponseEntity<FireEventEntity> result = this.fireEventService.updateIsVerified(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

        this.notifyFrontEnd();
        return result;
    };

    @PutMapping("/updateIdEquipeIntervention/{id}")
    public ResponseEntity<FireEventEntity> updateIdEquipeIntervention(@PathVariable Long id, @RequestBody FireEventDTO dto) throws Exception {
        ResponseEntity<FireEventEntity> result = this.fireEventService.updateIdEquipeIntervention(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

        this.notifyFrontEnd();
        return result;
    };
}
