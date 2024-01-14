package com.datacenter.webserver.controller;

import com.datacenter.webserver.dto.FireEventDTO;
import com.datacenter.webserver.dto.SensorDetectionVM;
import com.datacenter.webserver.models.Coords;
import com.datacenter.webserver.models.FireEventEntity;
import com.datacenter.webserver.models.SensorEntity;
import com.datacenter.webserver.service.implementations.FireEventHandlerServiceImpl;
import com.datacenter.webserver.service.implementations.SensorHandlerServiceImpl;
import com.datacenter.webserver.service.implementations.WebSocketService;
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

    @Autowired
    public FireEventController(FireEventHandlerServiceImpl fireEventService,
                               WebSocketService webSocketService, SensorHandlerServiceImpl sensorService) {
        super(fireEventService);
        this.webSocketService = webSocketService;
        this.sensorService = sensorService;
    }
    @Override
    String getEntityTopic() {
        return "fire-event";
    }

    @Override
    public ResponseEntity<FireEventEntity> create(SensorDetectionVM sensorDetectionVM) throws Exception {
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
        fireEventDTO.setId(0L);
        fireEventDTO.setSensorId(sensorDetectionVM.getId());
        fireEventDTO.setIntensity(sensorDetectionVM.getIntensity());
        fireEventDTO.setStartDate((java.sql.Date) Date.from(Instant.now()));
        fireEventDTO.setEndDate(null);
        fireEventDTO.setVerified(false);
        fireEventDTO.setHandled(false);
        fireEventDTO.setIdEquipeIntervention(null); // WTF ?
        
        fireEventDTO.setCoords(sensorCoordinates);
        
        return this.create(fireEventDTO);
    }
}
