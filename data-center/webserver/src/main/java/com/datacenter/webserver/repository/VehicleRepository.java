package com.datacenter.webserver.repository;

import com.datacenter.webserver.models.VehicleEntity;

import java.util.Optional;

public interface VehicleRepository extends DistributedRepository<VehicleEntity> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur

    @Override
    Optional<VehicleEntity> findById(Long id);
}
