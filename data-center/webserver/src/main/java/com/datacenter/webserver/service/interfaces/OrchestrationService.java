package com.datacenter.webserver.service.interfaces;

import com.datacenter.webserver.dto.BaseDTO;
import com.datacenter.webserver.models.BaseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface OrchestrationService<ENTITY extends BaseEntity, DTO extends BaseDTO> {
    Optional<ENTITY> create(@RequestBody DTO dto);
    Optional<ENTITY> createFromView(@RequestBody DTO dto);
    Optional<List<DTO>> getAll();
    Optional<ENTITY> update(Long id, DTO dto);
    Optional<ENTITY> updateVerificationStatus(Long id, BaseDTO dto);
}
