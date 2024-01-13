package com.datacenter.webserver.repository;

import com.datacenter.webserver.models.UserEntity;

import java.util.Optional;

public interface UserRepository extends DistributedRepository<UserEntity> {
    Integer countBy(); // Méthode pour lire depuis la base de données Simulateur

    @Override
    Optional<UserEntity> findById(Long id);
}

