package com.simulator.webserver.repository;

import com.simulator.webserver.models.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur
}