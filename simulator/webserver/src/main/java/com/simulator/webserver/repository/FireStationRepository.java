package com.simulator.webserver.repository;

import com.simulator.webserver.models.FireStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FireStationRepository extends JpaRepository<FireStationEntity, Long> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur

    @Override
    Optional<FireStationEntity> findById(Long id);
}