package com.simulator.webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.models.SensorEntity;
import com.simulator.webserver.service.DetecsService;

import com.simulator.webserver.models.CoordsEntity;
import com.simulator.webserver.models.DetecsEntity;
import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.models.SensorEntity;
import com.simulator.webserver.service.DetecsService;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/sensor")
public class SensorController {
    String url = "http://localhost:8000/";

    private final DetecsService detecsService;

    @Autowired
    public SensorController(DetecsService detecsService) {
        this.detecsService = detecsService;
    }

    @GetMapping("/")
    public String home(){
        return "Hello World!";
    }

    @GetMapping("/test")
    public String test(){
        CoordsEntity coordsEntity = new CoordsEntity(0, 0);
        SensorEntity sensorEntity = new SensorEntity("32", coordsEntity);
        DetecsEntity detecsEntity = new DetecsEntity(sensorEntity, null, 10);
        return detecsService.sendDetection(url,detecsEntity);
        //return requestService.send(url, "test requete");

    }

    @GetMapping("/{id}/{intensity}")
    public String sensor(@PathVariable String id, @PathVariable Long intensity){
        CoordsEntity coordsEntity = new CoordsEntity(0, 0);
        SensorEntity sensorEntity = new SensorEntity(id, coordsEntity);
        DetecsEntity detecsEntity = new DetecsEntity(sensorEntity, null, intensity);
        return detecsService.sendDetection(url,detecsEntity);

    }

}
