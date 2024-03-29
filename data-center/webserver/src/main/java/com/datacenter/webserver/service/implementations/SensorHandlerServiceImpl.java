package com.datacenter.webserver.service.implementations;

import com.datacenter.webserver.dto.BaseDTO;
import com.datacenter.webserver.dto.SensorDTO;
import com.datacenter.webserver.models.Coords;
import com.datacenter.webserver.models.SensorEntity;
import com.datacenter.webserver.repository.SensorRepository;
import com.datacenter.webserver.service.interfaces.AbstractOrchestrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SensorHandlerServiceImpl extends AbstractOrchestrationService<SensorEntity, SensorDTO> {

    @Autowired
    public SensorHandlerServiceImpl(SensorRepository sensorRepository) {
        this.repository = sensorRepository;
    }
    
    @Override
    public Optional<SensorEntity> create(SensorDTO sensorDTO) {
        // FIXME
        // sendSensorUpdate("${relay.url}", sensorDTO);
        // sendSensorUpdate("${passerelle.url}", sensorDTO);
        return Optional.of(
                this.repository.save(
                        SensorDTO.toEntity(sensorDTO)
                ));
    }

    @Override
    public Optional<SensorEntity> createFromView(SensorDTO dto) {
        return this.create(dto);
    }

    @Override
    public Optional<List<SensorDTO>> getAll() {
        return Optional.of(this.repository.findAll().stream().map(SensorEntity::toDTO).toList());
    }
    
    @Override 
    public Optional<SensorEntity> update(Long id, SensorDTO sensorDTO) {
        System.out.println("updated");
        Optional<SensorEntity> sensorEntity = Optional.of(this.repository.getReferenceById(id));
        if (sensorEntity.isEmpty()) {
            return sensorEntity;
        }
        
        Long id1 = sensorEntity.get().getId();
        sensorDTO.setId(id1);
        
        return Optional.of(this.repository.save(SensorDTO.toEntity(sensorDTO)));
    }

    @Override
    public Optional<SensorEntity> updateVerificationStatus(Long id, boolean isVerified) {
        return Optional.empty();
    }

    @Override
    public Optional<SensorEntity> updateCoords(Long id, Coords coords) {
        return Optional.empty();
    }

    @Override
    public Optional<SensorEntity> updateIsAvailable(Long id, boolean isAvailable) {
        return Optional.empty();
    }

    public Optional<SensorEntity> getFromId(Long id) {
        return this.repository.findById(id);
    }

    public Optional<SensorEntity> updateVerificationStatus(Long id, BaseDTO dto) {
        return Optional.empty();
    }

    //  TODO
    /*
    @Override
    public Optional<SensorEntity> update(Long id, SensorDTO sensorDTO) {
        if (sensorDTO == null || ObjectUtils.isEmpty(sensorDTO) || !Objects.equals(sensorDTO.getId(), id)) {
            return Optional.empty();
        }

        Optional<SensorEntity> sensorToUpdate = this.repository.findById(id);
        if (sensorToUpdate.isEmpty()) {
            return Optional.empty();
        }
        sensorToUpdate
                .map(sensorEntity -> SensorDTO.toEntity(sensorDTO));
//        sendSensorUpdate("${relay.url}", sensorDTO);
        sendSensorUpdate("${passerelle.url}" , sensorDTO);

        return Optional.of(this.repository.save(sensorToUpdate.get()));    }

    private void sendSensorUpdate(String url, SensorDTO sensorDTO){ try {
        String json = objectMapper.writeValueAsString(sensorDTO);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        String response = restTemplate.exchange(url , HttpMethod.POST, requestEntity, String.class).getBody();

        // Affiche la réponse
        log.debug("Réponse du serveur : {}", response);
        } catch (JsonProcessingException e) {
            // Gérer l'exception, par exemple en l'affichant
            e.printStackTrace();
        }
    }
    
     */
}
