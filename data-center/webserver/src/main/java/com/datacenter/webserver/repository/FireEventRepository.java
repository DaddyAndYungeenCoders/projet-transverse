package com.datacenter.webserver.repository;

import com.datacenter.webserver.models.FireEventEntity;

import java.util.Optional;

public interface FireEventRepository extends DistributedRepository<FireEventEntity> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur

    @Override
    Optional<FireEventEntity> findById(Long id);
}
