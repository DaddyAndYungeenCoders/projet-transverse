package com.datacenter.webserver.controller;

import com.datacenter.webserver.dto.BaseDTO;
import com.datacenter.webserver.dto.SensorDetectionVM;
import com.datacenter.webserver.models.BaseEntity;
import com.datacenter.webserver.service.implementations.WebSocketService;
import com.datacenter.webserver.service.interfaces.OrchestrationService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Getter
public abstract class CRUDController<ENTITY extends BaseEntity, DTO extends BaseDTO> {
    
    @Autowired
    WebSocketService webSocketService;
    
    private final OrchestrationService<ENTITY, DTO> service;

    abstract String getEntityTopic();
    
    @GetMapping("/fetch-all")
    public ResponseEntity<List<DTO>> fetchAll() {
        List<DTO> DTOList = service.getAll()
                .orElse(Collections.emptyList());
        
        return  ResponseEntity.ok(DTOList);
    };
    
    @GetMapping("/create/dto")
    public ResponseEntity<ENTITY> create(@RequestBody DTO dto) throws Exception {
        System.out.println("[REST] - request to create an element");
        ResponseEntity<ENTITY> result = this.service.create(dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        
        // TODO POST SERVICE SEND
        this.notifyFrontEnd();
        return result;
    };
    
    @PostMapping("/create")
    abstract public ResponseEntity<ENTITY> create(@RequestBody SensorDetectionVM sensorDetectionVM) throws Exception;
    
    @PutMapping("/update/{id}")
    public ResponseEntity<ENTITY> update(@PathVariable Long id, @RequestBody DTO dto) throws Exception {
        ResponseEntity<ENTITY> result = this.service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        
        this.notifyFrontEnd();
        return result;
    };

    protected void notifyFrontEnd() {
        System.out.println("[WEBSOCKET] - web socket sending a refresh socket to frontend");
        final String entityTopic = getEntityTopic();
        if (StringUtils.isBlank(entityTopic)) {
            return;
        }
        webSocketService.sendMessage(entityTopic);
    }
}
