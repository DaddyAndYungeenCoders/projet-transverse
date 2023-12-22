package com.simulator.webserver.repository;

import com.simulator.webserver.models.HistoriqueEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoriqueRepository extends JpaRepository<HistoriqueEntity, Long> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur
}