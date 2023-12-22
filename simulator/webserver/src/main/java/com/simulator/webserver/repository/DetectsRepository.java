package com.simulator.webserver.repository;

import com.simulator.webserver.models.DetectsEntity;
import com.simulator.webserver.models.PK.DetectsEntityPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DetectsRepository extends JpaRepository<DetectsEntity, DetectsEntityPK> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur
}