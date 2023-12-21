package com.simulator.webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.simulator.webserver.service.RequestService;

@RestController
public class Controller {
    String url = "http://localhost:8000/";

    private final RequestService requestService;

    @Autowired
    public Controller(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/")
    public String home(){
        return "Hello World!";
    }

    @GetMapping("/test")
    public String test(){
        return requestService.send_sensor(url,"89", 8);
        //return requestService.send(url, "test requete");

    }

    @GetMapping("/{id}/{intensity}")
    public String sensor(@PathVariable String id, @PathVariable Long intensity){
        return requestService.send_sensor(url, id, intensity);
    }

}
