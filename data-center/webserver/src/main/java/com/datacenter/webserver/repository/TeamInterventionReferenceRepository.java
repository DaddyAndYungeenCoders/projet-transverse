package com.datacenter.webserver.repository;

import com.datacenter.webserver.models.TeamInterventionReferenceEntity;

import java.util.Optional;

public interface TeamInterventionReferenceRepository extends DistributedRepository<TeamInterventionReferenceEntity> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur

    @Override
    Optional<TeamInterventionReferenceEntity> findById(Long id);
}
