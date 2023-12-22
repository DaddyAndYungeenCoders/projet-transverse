package com.simulator.webserver.controller;

import com.simulator.webserver.models.UserEntity;
import com.simulator.webserver.service.DBService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

    @GetMapping("/new")
    public String newuser() {
        UserEntity userEntity = new UserEntity(1L, "Jean", "ffergf", null);
        return dbService.save(userEntity).getUsername();
    }
}
