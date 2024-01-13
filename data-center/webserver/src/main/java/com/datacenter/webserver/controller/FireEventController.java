package com.datacenter.webserver.controller;

import com.datacenter.webserver.dto.FireEventDTO;
import com.datacenter.webserver.models.FireEventEntity;
import com.datacenter.webserver.service.implementations.FireEventHandlerServiceImpl;
import com.datacenter.webserver.service.implementations.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fire-event")
public class FireEventController extends CRUDController<FireEventEntity, FireEventDTO> {

    @Autowired
    public FireEventController(FireEventHandlerServiceImpl fireEventService,
                               WebSocketService webSocketService) {
        super(fireEventService);
        this.webSocketService = webSocketService;
    }
    
    @Override
    String getEntityTopic() {
        return "fire-event";
    }
}
