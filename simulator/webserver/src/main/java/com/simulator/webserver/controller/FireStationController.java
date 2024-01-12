package com.simulator.webserver.controller;

import com.simulator.webserver.dto.FireStationDTO;
import com.simulator.webserver.models.FireStationEntity;
import com.simulator.webserver.service.interfaces.FireStationHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fire-station")
public class FireStationController extends AbstractController<FireStationEntity, FireStationDTO> {

    @Autowired
    private final FireStationHandlerService fireStationHandlerService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<FireStationEntity> create(@RequestBody FireStationDTO fireStation) {
        System.out.println("[REST] - Request to create a fire-station " + fireStation);
        ResponseEntity<FireStationEntity> result = this.fireStationHandlerService.createFireStation(fireStation)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());
        
        this.notifyFrontEnd();
        
        return result;
    }

    @Override
    @GetMapping("/fetch-all")
    public ResponseEntity<List<FireStationDTO>> fetchAll() {
        List<FireStationDTO> fireEventDTOList = fireStationHandlerService.getAllFireStations()
                .map(fireStationEntities -> fireStationEntities.stream()
                        .map(FireStationEntity::toDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        return ResponseEntity.ok(fireEventDTOList);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<FireStationEntity> update(@PathVariable Long id, @RequestBody FireStationDTO fireStationDTO) {
        ResponseEntity<FireStationEntity> result = this.fireStationHandlerService.updateFireStation(id, fireStationDTO)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());
        
        this.notifyFrontEnd();
        
        return result;
    }

    @Override
    String getEntityTopic() {
        return "fire-station";
    }
}