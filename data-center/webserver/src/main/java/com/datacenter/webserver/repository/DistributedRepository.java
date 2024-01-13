package com.datacenter.webserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface DistributedRepository<ENTITY> extends JpaRepository<ENTITY, Long> {
    
}
