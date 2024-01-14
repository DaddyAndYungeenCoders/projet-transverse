package com.datacenter.webserver.controller;

import com.datacenter.webserver.dto.SensorDetectionVM;
import com.datacenter.webserver.dto.TeamDTO;
import com.datacenter.webserver.models.Coords;
import com.datacenter.webserver.models.TeamEntity;
import com.datacenter.webserver.service.implementations.TeamHandlerServiceImpl;
import com.datacenter.webserver.service.implementations.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/team")
public class TeamController extends CRUDController<TeamEntity, TeamDTO>{
    
    @Autowired
    public TeamController(TeamHandlerServiceImpl teamHandlerService, WebSocketService webSocketService) {
        super(teamHandlerService);
        this.webSocketService = webSocketService;
    }

    @Override
    String getEntityTopic() {
        return "intervention";
    }

    @Override
    public ResponseEntity<TeamEntity> create(SensorDetectionVM sensorDetectionVM) throws Exception {
        return null;
    }
    
    @PutMapping("/coords/{id}")
    public ResponseEntity<TeamEntity> updateCoords(@PathVariable Long id, @RequestBody Coords newCoords) {
        Optional<TeamEntity> updatedTeam = this.service.updateCoords(id, newCoords);

        if (updatedTeam.isPresent()) {
            this.notifyFrontEnd();
        }
        
        return updatedTeam.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/available/{id}/{isAvailable}")
    public ResponseEntity<TeamEntity> updateIsAvailable(@PathVariable Long id, @PathVariable boolean isAvailable) {
        Optional<TeamEntity> updatedTeam = this.service.updateIsAvailable(id, isAvailable);

        if (updatedTeam.isPresent()) {
            this.notifyFrontEnd();
        }

        return updatedTeam.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
