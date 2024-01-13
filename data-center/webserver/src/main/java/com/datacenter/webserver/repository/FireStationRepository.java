package com.datacenter.webserver.repository;

import com.datacenter.webserver.models.FireStationEntity;

import java.util.Optional;

public interface FireStationRepository extends DistributedRepository<FireStationEntity> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur

    @Override
    Optional<FireStationEntity> findById(Long id);
}
