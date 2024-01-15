package com.simulator.webserver.controller;

import com.simulator.webserver.dto.BaseDTO;
import com.simulator.webserver.models.BaseEntity;
import com.simulator.webserver.service.implementations.WebSocketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

// TODO factorize controllers
public abstract class AbstractController<ENTITY extends BaseEntity, DTO extends BaseDTO> {
    
    @Autowired
    WebSocketService webSocketService;
    
    abstract String getEntityTopic();
    abstract ResponseEntity<List<DTO>> fetchAll();
    abstract ResponseEntity<ENTITY> create(DTO dto) throws Exception;
    abstract ResponseEntity<ENTITY> update(Long id, DTO dto) throws Exception;
    
    protected void notifyFrontEnd() {
        System.out.println("[WEBSOCKET] - web socket sending a refresh socket to frontend with topic " + getEntityTopic());
        final String entityTopic = getEntityTopic();
        if (StringUtils.isBlank(entityTopic)) {
            return;
        }
        webSocketService.sendMessage(entityTopic);
    }
}
