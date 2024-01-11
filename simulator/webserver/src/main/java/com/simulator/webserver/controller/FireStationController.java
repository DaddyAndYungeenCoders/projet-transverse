package com.simulator.webserver.controller;

import com.simulator.webserver.dto.FireStationDTO;
import com.simulator.webserver.models.FireStationEntity;
import com.simulator.webserver.service.interfaces.FireStationHandlerService;
import com.simulator.webserver.service.interfaces.mappers.FireStationMapper;
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
@RequestMapping("/api/fire-station")
public class FireStationController {

    private final FireStationHandlerService fireStationHandlerService;

    @PostMapping("/create")
    public ResponseEntity<FireStationEntity> createFireStationFromView(@RequestBody FireStationDTO fireStation) {
        System.out.println("[REST] - Request to create a fire-station " + fireStation);
        return this.fireStationHandlerService.createFireStation(fireStation)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<List<FireStationDTO>> getAllFireStations() {
        List<FireStationDTO> fireEventDTOList = fireStationHandlerService.getAllFireStations()
                .map(fireStationEntities -> fireStationEntities.stream()
                        .map(FireStationMapper.MAPPER::toDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        return ResponseEntity.ok(fireEventDTOList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FireStationEntity> updateFireStation(@PathVariable Long id, @RequestBody FireStationDTO fireStationDTO) {
        return this.fireStationHandlerService.updateFireStation(id, fireStationDTO)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}