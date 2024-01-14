package com.simulator.webserver.controller;

import com.simulator.webserver.dto.FireStationDTO;
import com.simulator.webserver.dto.TeamDTO;
import com.simulator.webserver.models.FireStationEntity;
import com.simulator.webserver.models.TeamEntity;
import com.simulator.webserver.service.interfaces.TeamHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/team")
public class TeamController extends AbstractController<TeamEntity, TeamDTO>{
    @Autowired
    private final TeamHandlerService teamHandlerService;

    @Override
    @GetMapping("/fetch-all")
    ResponseEntity<List<TeamDTO>> fetchAll() {
        List<TeamDTO> teamDTOList = teamHandlerService.fetchAll()
                .map(teamEntities -> teamEntities.stream()
                        .map(TeamEntity::toDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        return ResponseEntity.ok(teamDTOList);
    }

    @Override
    @PostMapping("/create")
    ResponseEntity<TeamEntity> create(@RequestBody TeamDTO teamDTO) {
        ResponseEntity<TeamEntity> result = this.teamHandlerService.createTeam(teamDTO)
                .map(team -> ResponseEntity.ok().body(team))
                .orElseGet(() -> ResponseEntity.notFound().build());

        this.notifyFrontEnd();

        return result;
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<TeamEntity> update(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        ResponseEntity<TeamEntity> result = this.teamHandlerService.update(id, teamDTO)
                .map(fire -> ResponseEntity.ok().body(fire))
                .orElseGet(() -> ResponseEntity.notFound().build());

        this.notifyFrontEnd();

        return result;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TeamEntity> getTeamById(@PathVariable Long id) {

        return this.teamHandlerService.getTeamById(id)
                .map(team -> ResponseEntity.ok().body(team))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    String getEntityTopic() {
        return "intervention";
    }
}