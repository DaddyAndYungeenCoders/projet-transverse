package com.simulator.webserver.controller;

import com.simulator.webserver.dto.SensorDTO;
import com.simulator.webserver.models.SensorEntity;
import com.simulator.webserver.service.interfaces.SensorHandlerService;
import com.simulator.webserver.service.interfaces.mappers.SensorMapper;
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
public class SensorController {

    private final SensorHandlerService sensorHandlerService;

    @PostMapping("/create")
    public ResponseEntity<SensorEntity> createSensorFromView(@RequestBody SensorDTO sensor) {
        System.out.println("[REST] - Request to create a fire-station " + sensor);
        return this.sensorHandlerService.createSensor(sensor)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create/generator")
    public ResponseEntity<SensorEntity> createSensorFromGenerator(@RequestBody SensorDTO sensor) {
        System.out.println("[REST] - Request to create a sensor " + sensor);
        return this.sensorHandlerService.createSensor(sensor)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<List<SensorDTO>> getAllSensors() {
        List<SensorDTO> sensorDTOList = sensorHandlerService.getAllSensors()
                .map(sensors -> sensors.stream()
                        .map(SensorMapper.MAPPER::toDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        return ResponseEntity.ok(sensorDTOList.isEmpty() ? null : sensorDTOList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SensorEntity> updateSensorFromId(@PathVariable Long id, @RequestBody SensorDTO sensorDTO) {
        return this.sensorHandlerService.updateSensor(id, sensorDTO)
                .map(sensor -> ResponseEntity.ok().body(sensor))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
