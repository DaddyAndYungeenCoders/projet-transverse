package com.datacenter.webserver.repository;

import com.datacenter.webserver.models.TeamEntity;

import java.util.Optional;

public interface TeamRepository extends DistributedRepository<TeamEntity> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur

    @Override
    Optional<TeamEntity> findById(Long id);
}
