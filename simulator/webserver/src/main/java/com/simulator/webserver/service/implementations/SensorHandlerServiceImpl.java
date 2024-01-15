package com.simulator.webserver.service.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.dto.SensorDTO;
import com.simulator.webserver.models.SensorEntity;
import com.simulator.webserver.repository.SensorRepository;
import com.simulator.webserver.service.PostService;
import com.simulator.webserver.service.interfaces.SensorHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class SensorHandlerServiceImpl implements SensorHandlerService {
    @Autowired
    private SensorRepository sensorRepository;
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private ObjectMapper objectMapper = new ObjectMapper();
    private PostService postService = new PostService();
    @Value("${relay.url}")
    private String relayURL;
    @Value("${passerelle.url}")
    private String passerelleURL;

    @Override
    public Optional<SensorEntity> createSensor(SensorDTO sensorDTO) {
        return Optional.of(
                this.sensorRepository.save(
                        SensorDTO.toEntity(sensorDTO)
                ));
    }

    @Override
    public Optional<List<SensorEntity>> getAllSensors() {
        return Optional.of(this.sensorRepository.findAll());
    }

    @Override
    public Optional<SensorEntity> updateSensor(Long id, SensorDTO sensorDTO) {
        if (sensorDTO == null || ObjectUtils.isEmpty(sensorDTO) || !Objects.equals(sensorDTO.getId(), id)) {
            return Optional.empty();
        }

        Optional<SensorEntity> sensorToUpdate = this.sensorRepository.findById(id);
        if (sensorToUpdate.isEmpty()) {
            return Optional.empty();
        }
        sensorToUpdate
                .map(sensorEntity -> SensorDTO.toEntity(sensorDTO));
        postService.sendObject(passerelleURL , sensorDTO);

        return Optional.of(this.sensorRepository.save(sensorToUpdate.get()));    }

    @Override
    public Optional<SensorEntity> updateSensorIntensity(Long id, int intensity) {
        Optional<SensorEntity> sensorToUpdate = this.sensorRepository.findById(id);
        if (sensorToUpdate.isEmpty()) {
            return Optional.empty();
        }
        sensorToUpdate.get().setIntensity(intensity);
     //   postService.sendObject(passerelleURL , SensorEntity.toDTO(sensorToUpdate.get()));

        return Optional.of(this.sensorRepository.save(sensorToUpdate.get()));
    }

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
}
