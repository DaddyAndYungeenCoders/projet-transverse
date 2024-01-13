package com.simulator.webserver.service.interfaces;

import com.simulator.webserver.models.DetectsEntity;
import com.simulator.webserver.models.PK.DetectsEntityPK;

import java.util.List;
import java.util.Optional;

public interface DetectsHandlerService {
    Optional<List<DetectsEntity>> findAllFiresDetectedBySensorId(Long sensorId);
}
