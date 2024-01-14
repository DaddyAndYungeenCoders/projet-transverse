package com.simulator.webserver.service;


import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Service
@Slf4j
public class DBService {
    
    private final UsersRepository usersRepository;

    public DBService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Integer read() {
        try {
            return usersRepository.countBy();
        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture depuis la base de données : " + e.getMessage());
            return -1; // Valeur par défaut en cas d'erreur

        }
    }

    public List<UserEntity> findAll(){
        return usersRepository.findAll();
    }

    public UserEntity save(UserEntity userEntity){
        return usersRepository.save(userEntity);
    }
}