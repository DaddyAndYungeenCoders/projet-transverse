package com.simulator.webserver.repository;

import com.simulator.webserver.models.FireEventEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FireEventRepository extends JpaRepository<FireEventEntity, Long> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur

    @Override
    Optional<FireEventEntity> findById(Long id);
}