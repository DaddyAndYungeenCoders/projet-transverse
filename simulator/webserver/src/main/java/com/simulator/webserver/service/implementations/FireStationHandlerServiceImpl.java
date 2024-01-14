package com.simulator.webserver.service.implementations;

import com.simulator.webserver.dto.FireStationDTO;
import com.simulator.webserver.models.FireStationEntity;
import com.simulator.webserver.repository.FireStationRepository;
import com.simulator.webserver.service.interfaces.FireStationHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FireStationHandlerServiceImpl implements FireStationHandlerService {
    @Autowired
    private FireStationRepository fireStationRepository;
    
    @Override
    public Optional<FireStationEntity> createFireStation(FireStationDTO fireStationDTO) {
        return Optional.of(
                this.fireStationRepository.save(
                        FireStationDTO.toEntity(fireStationDTO)
                ));
    }

    @Override
    public Optional<FireStationEntity> createFireStationFromView(FireStationDTO fireStationDTO) {
        return this.createFireStation(fireStationDTO);
    }

    @Override
    public Optional<List<FireStationEntity>> getAllFireStations() {
        return Optional.of(fireStationRepository.findAll());
    }

    @Override
    public Optional<FireStationEntity> updateFireStation(Long id, FireStationDTO fireStationDTO) {
        if (fireStationDTO == null || ObjectUtils.isEmpty(fireStationDTO) || !Objects.equals(fireStationDTO.getId(), id)) {
            return Optional.empty();
        }
        
        Optional<FireStationEntity> fireStationToUpdate = this.fireStationRepository.findById(id);
        if (fireStationToUpdate.isEmpty()) {
            return Optional.empty();
        }
        fireStationToUpdate
                .map(fireStation -> FireStationDTO.toEntity(fireStationDTO));
        return Optional.of(this.fireStationRepository.save(fireStationToUpdate.get()));
    }

    @Override
    public Optional<FireStationEntity> getFireStaionByID(Long idFirestation) {
        return this.fireStationRepository.findById(idFirestation);
    }
}
