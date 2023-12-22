package com.simulator.webserver.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.simulator.webserver.repository.UsersRepository;
import com.simulator.webserver.models.UserEntity;
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

    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }
}