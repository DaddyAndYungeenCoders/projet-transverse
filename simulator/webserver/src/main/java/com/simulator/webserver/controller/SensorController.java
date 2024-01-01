package com.simulator.webserver.controller;

import com.simulator.webserver.models.CoordsEntity;
import com.simulator.webserver.models.DetectsEntity;
import com.simulator.webserver.models.SensorEntity;
import com.simulator.webserver.service.DetectsService;
import com.simulator.webserver.service.SensorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/sensor")
public class SensorController {
    String url = "http://localhost:8000/";

    private final DetectsService detectsService;
    private final SensorService sensorService;

    public SensorController(DetectsService detectsService, SensorService sensorService) {
        this.detectsService = detectsService;
        this.sensorService = sensorService;
    }

    @GetMapping("/")
    public String home(){
        return "Hello World!";
    }

    @GetMapping("/test")
    public String test(){
        CoordsEntity coordsEntity = new CoordsEntity(0, 0);
        SensorEntity sensorEntity = new SensorEntity(32L, coordsEntity);
        DetectsEntity detectsEntity = new DetectsEntity(sensorEntity, null, 10);
        return detectsService.sendDetection(url, detectsEntity);
        //return requestService.send(url, "test requete");

    }

    @GetMapping("/{id}/{intensity}")
    public String sensor(@PathVariable Long id, @PathVariable Long intensity){
        CoordsEntity coordsEntity = new CoordsEntity(0, 0);
        SensorEntity sensorEntity = new SensorEntity(id, coordsEntity);
        DetectsEntity detectsEntity = new DetectsEntity(sensorEntity, null, intensity);
        return detectsService.sendDetection(url,detectsEntity);

    }
    @GetMapping("/getAll")
    public List<SensorEntity> getAll(){
        return sensorService.findAll();
    }

    @GetMapping("/get/{id}")
    public SensorEntity getSensor(@PathVariable Long id){
        return sensorService.getSensor(id);
    }

}
