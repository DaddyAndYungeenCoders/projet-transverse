package com.datacenter.webserver.controller;

import com.datacenter.webserver.dto.SensorDTO;
import com.datacenter.webserver.dto.SensorDetectionVM;
import com.datacenter.webserver.models.SensorEntity;
import com.datacenter.webserver.service.implementations.SensorHandlerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensor")
public class SensorController extends CRUDController<SensorEntity, SensorDTO> {
    public SensorController(SensorHandlerServiceImpl sensorService) {
        super(sensorService);
    }

    @Override
    String getEntityTopic() {
        return "fire-event";
    }

    @Override
    public ResponseEntity<SensorEntity> create(SensorDetectionVM sensorDetectionVM) throws Exception {
        return null;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SensorEntity> getById(@PathVariable Long id) {
        return this.service.getFromId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
