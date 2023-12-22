package com.simulator.webserver.repository;

import com.simulator.webserver.models.DetecsEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DetecsRepository extends JpaRepository<DetecsEntity, Long> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur
}