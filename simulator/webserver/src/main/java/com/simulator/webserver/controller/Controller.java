package com.simulator.webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.webserver.models.UserEntity;
import com.simulator.webserver.service.DBService;

@RestController
@RequestMapping("/db")
public class Controller {
    String url = "http://localhost:8000/";
    
    private final DBService dbService;

    //private final DetecsService detecsService;
    public Controller(DBService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("/read-from-db")
    public String readFromDB(){
        Integer rowCount = dbService.read();

        if (rowCount != null) {
            return "Nombre de lignes dans la table : " + rowCount;
        } else {
            return "Erreur lors de la lecture depuis la base de données";
        }
    }

    @GetMapping("/")
    public String home(){
        return "Hello World!";
    }

    @GetMapping("/all")
    public String findall(){
        return dbService.findAll().get(0).getUsername();
    }


}
