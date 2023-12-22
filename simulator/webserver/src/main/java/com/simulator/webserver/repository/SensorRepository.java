package com.simulator.webserver.repository;

import com.simulator.webserver.models.SensorEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Long> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur
}