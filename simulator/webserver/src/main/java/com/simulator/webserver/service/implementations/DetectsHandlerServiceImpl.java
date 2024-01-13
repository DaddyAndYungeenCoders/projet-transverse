package com.simulator.webserver.service.implementations;

import com.simulator.webserver.models.DetectsEntity;
import com.simulator.webserver.repository.DetectsRepository;
import com.simulator.webserver.service.interfaces.DetectsHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetectsHandlerServiceImpl implements DetectsHandlerService {
    @Autowired
    private DetectsRepository detectsRepository;
    
    @Override
    public Optional<List<DetectsEntity>> findAllFiresDetectedBySensorId(Long sensorId) {
        return detectsRepository.findByIdSensor(sensorId);
    }
}
