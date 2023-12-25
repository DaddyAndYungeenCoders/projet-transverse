package com.simulator.webserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simulator.webserver.models.SensorEntity;
import com.simulator.webserver.service.DetectsService;

import com.simulator.webserver.models.Coords;
import com.simulator.webserver.models.DetectsEntity;


@RestController
@RequestMapping("/sensor")
public class SensorController {
    String url = "http://localhost:8000/";

    private final DetectsService detectsService;

    public SensorController(DetectsService detectsService) {
        this.detectsService = detectsService;
    }

    @GetMapping("/")
    public String home(){
        return "Hello World!";
    }

    @GetMapping("/{id}}")
    public String getSensor(@PathVariable Long id){
        DetectsEntity detectsEntity = new DetectsEntity();
        return detectsService.sendDetection(url,detectsEntity);
    }

}
