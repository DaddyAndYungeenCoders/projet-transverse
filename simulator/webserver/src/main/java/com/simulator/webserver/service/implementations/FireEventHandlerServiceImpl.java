package com.simulator.webserver.service.implementations;

import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.repository.FireEventRepository;
import com.simulator.webserver.service.interfaces.FireEventHandlerService;
import com.simulator.webserver.service.interfaces.mappers.FireEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FireEventHandlerServiceImpl implements FireEventHandlerService {
    @Autowired
    private FireEventRepository fireEventRepository;

    @Override
    public Optional<FireEventEntity> createFireEvent(FireEventDTO fireEventDTO) {
        return Optional.of(fireEventRepository.save(FireEventMapper.MAPPER.toEntity(fireEventDTO)));
    }

    @Override
    public Optional<FireEventEntity> createFireEventFromView(FireEventDTO fireEventDTO) {
        return this.createFireEvent(fireEventDTO); // TODO discuter de la pertinence
    }

    @Override
    public Optional<List<FireEventEntity>> getAllFireEvents() {
        return Optional.of(fireEventRepository.findAll());
    }
}
