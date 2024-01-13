package com.simulator.webserver.repository;

import com.simulator.webserver.models.DetectsEntity;
import com.simulator.webserver.models.PK.DetectsEntityPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DetectsRepository extends JpaRepository<DetectsEntity, DetectsEntityPK> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur
    Optional<List<DetectsEntity>> findByIdSensor(Long sensorId);
}