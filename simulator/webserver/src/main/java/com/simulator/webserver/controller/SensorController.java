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

    @GetMapping("/test")
    public String test(){
        Coords coordsEntity = new Coords(0, 0);
        SensorEntity sensorEntity = new SensorEntity(32L, coordsEntity);
        DetectsEntity detectsEntity = new DetectsEntity(sensorEntity, null, 10);
        return detectsService.sendDetection(url, detectsEntity);
        //return requestService.send(url, "test requete");

    }

    @GetMapping("/{id}/{intensity}")
    public String sensor(@PathVariable Long id, @PathVariable Long intensity){
        Coords coordsEntity = new Coords(0, 0);
        SensorEntity sensorEntity = new SensorEntity(id, coordsEntity);
        DetectsEntity detectsEntity = new DetectsEntity(sensorEntity, null, intensity);
        return detectsService.sendDetection(url,detectsEntity);

    }

}
