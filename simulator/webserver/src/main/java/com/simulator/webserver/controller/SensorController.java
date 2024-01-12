package com.simulator.webserver.controller;

import com.simulator.webserver.dto.SensorDTO;
import com.simulator.webserver.models.SensorEntity;
import com.simulator.webserver.service.interfaces.SensorHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sensor")
public class SensorController extends AbstractController<SensorEntity, SensorDTO> {

    private final SensorHandlerService sensorHandlerService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<SensorEntity> create(@RequestBody SensorDTO sensor) {
        System.out.println("[REST] - Request to create a sensor " + sensor);
        ResponseEntity<SensorEntity> result = this.sensorHandlerService.createSensor(sensor)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());

        this.notifyFrontEnd();

        return result;
    }

    @PostMapping("/create/generator")
    public ResponseEntity<SensorEntity> createSensorFromGenerator(@RequestBody SensorDTO sensor) {
        System.out.println("[REST] - Request to create a sensor " + sensor);
        ResponseEntity<SensorEntity> result = this.sensorHandlerService.createSensor(sensor)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());

        this.notifyFrontEnd();

        return result;
    }

    @Override
    @GetMapping("/fetch-all")
    public ResponseEntity<List<SensorDTO>> fetchAll() {
        List<SensorDTO> sensorDTOList = sensorHandlerService.getAllSensors()
                .map(sensors -> sensors.stream()
                        .map(SensorEntity::toDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        return ResponseEntity.ok(sensorDTOList);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<SensorEntity> update(@PathVariable Long id, @RequestBody SensorDTO sensorDTO) {
        ResponseEntity<SensorEntity> result = this.sensorHandlerService.updateSensor(id, sensorDTO)
                .map(sensor -> ResponseEntity.ok().body(sensor))
                .orElseGet(() -> ResponseEntity.notFound().build());
        
        this.notifyFrontEnd();

        return result;
    }

    @Override
    String getEntityTopic() {
        return "sensor";
    }
}
