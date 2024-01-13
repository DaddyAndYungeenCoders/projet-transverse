package com.datacenter.webserver.repository;

import com.datacenter.webserver.models.SensorEntity;

import java.util.Optional;

public interface SensorRepository extends DistributedRepository<SensorEntity> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur

    @Override
    Optional<SensorEntity> findById(Long id);
}
