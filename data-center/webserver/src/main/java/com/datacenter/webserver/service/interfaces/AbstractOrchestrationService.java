package com.datacenter.webserver.service.interfaces;

import com.datacenter.webserver.dto.BaseDTO;
import com.datacenter.webserver.models.BaseEntity;
import com.datacenter.webserver.repository.DistributedRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractOrchestrationService<ENTITY extends BaseEntity, DTO extends BaseDTO> implements OrchestrationService<ENTITY, DTO> {
    @Autowired
    protected DistributedRepository<ENTITY> repository;
}
