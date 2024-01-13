package com.simulator.webserver.service.interfaces;

import com.simulator.webserver.dto.FireStationDTO;
import com.simulator.webserver.models.FireStationEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface FireStationHandlerService {
    Optional<FireStationEntity> createFireStation(FireStationDTO fireStationDTO);
    Optional<FireStationEntity> createFireStationFromView(FireStationDTO fireStationDTO);
    Optional<List<FireStationEntity>> getAllFireStations();
    Optional<FireStationEntity> updateFireStation(Long id, FireStationDTO fireStationDTO);
}
