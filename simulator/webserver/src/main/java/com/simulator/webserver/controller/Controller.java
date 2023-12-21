package com.simulator.webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.models.SensorEntity;
import com.simulator.webserver.service.DetecsService;

@RestController
public class Controller {
    String url = "http://localhost:8000/";

    private final DetecsService detecsService;

    @Autowired
    public Controller(DetecsService detecsService) {
        this.detecsService = detecsService;
    }

    @GetMapping("/")
    public String home(){
        return "Hello World!";
    }

    @GetMapping("/test")
    public String test(){
        SensorEntity sensorEntity = new SensorEntity()
        return detecsService.sendDetection(null);
        //return requestService.send(url, "test requete");

    }

    @GetMapping("/{id}/{intensity}")
    public String sensor(@PathVariable String id, @PathVariable Long intensity){
        return detecsService.sendDetection(null);

    }

}
