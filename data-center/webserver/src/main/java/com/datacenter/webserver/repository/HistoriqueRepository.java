package com.datacenter.webserver.repository;

import com.datacenter.webserver.models.HistoriqueEntity;

import java.util.Optional;

public interface HistoriqueRepository extends DistributedRepository<HistoriqueEntity> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur

    @Override
    Optional<HistoriqueEntity> findById(Long id);
}

