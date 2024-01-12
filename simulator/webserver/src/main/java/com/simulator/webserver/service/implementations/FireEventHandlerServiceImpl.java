package com.simulator.webserver.service.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.repository.FireEventRepository;
import com.simulator.webserver.service.interfaces.FireEventHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FireEventHandlerServiceImpl implements FireEventHandlerService {
    @Autowired
    private FireEventRepository fireEventRepository;
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Optional<FireEventEntity> createFireEvent(FireEventDTO fireEventDTO) {
        FireEventEntity entity = new FireEventEntity();
        entity.set_real(fireEventDTO.isReal());
        entity.setCoords(fireEventDTO.getCoords());
        entity.setEnd_date(null);
        entity.setStart_date(fireEventDTO.getStartDate());
        entity.setReal_intensity(fireEventDTO.getRealIntensity());

        sendFireEventToManager("http://127.0.0.1:8000", fireEventDTO);
        return Optional.of(fireEventRepository.save(entity));
    }

    private void sendFireEventToManager(String url, FireEventDTO fireEventDTO){
        try {
            String json = objectMapper.writeValueAsString(fireEventDTO);
            HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
            String response = restTemplate.exchange(url + "/api/view/fire-event/", HttpMethod.POST, requestEntity, String.class).getBody();

            // Affiche la réponse
            log.debug("Réponse du serveur : {}", response);
        } catch (JsonProcessingException e) {
            // Gérer l'exception, par exemple en l'affichant
            e.printStackTrace();
        }
    }

    @Override
    public Optional<FireEventEntity> createFireEventFromView(FireEventDTO fireEventDTO) {
        return this.createFireEvent(fireEventDTO); // TODO discuter de la pertinence
    }

    @Override
    public Optional<List<FireEventEntity>> getAllFireEvents() {
        return Optional.of(fireEventRepository.findAll());
    }

    @Override
    public Optional<FireEventEntity> updateFireEvent(Long id, int intensity) {
        Optional<FireEventEntity> fireToUpdate = this.fireEventRepository.findById(id);
        if (fireToUpdate.isEmpty()) {
            return Optional.empty();
        }
        
        FireEventEntity fire = fireToUpdate.get();
        fire.setReal_intensity(intensity);
        return Optional.of(this.fireEventRepository.save(fire));
    }

    @Override
    public Optional<FireEventEntity> updateFireEvent(Long id, FireEventDTO fireEventDTO) {
        Optional<FireEventEntity> fireToUpdate = this.fireEventRepository.findById(id);
        if (fireToUpdate.isEmpty()) {
            return Optional.empty();
        }
        FireEventEntity fire = fireToUpdate.get();
        updateFireEventData(fireEventDTO, fire);
        return Optional.of(this.fireEventRepository.save(fire));
    }

    @Override
    public Optional<Boolean> isFireReal(Long id) {
        Optional<FireEventEntity> fireEventEntity = this.fireEventRepository.findById(id);
        return fireEventEntity.map(FireEventEntity::is_real);
    }

    //TODO Mapper with MapStruct instead of this function
    private void updateFireEventData(FireEventDTO fireEventDTO, FireEventEntity fireEventEntity) {
        fireEventEntity.setReal_intensity(fireEventDTO.getRealIntensity());
        fireEventEntity.set_real(fireEventDTO.isReal());
        fireEventEntity.setStart_date(fireEventDTO.getStartDate());
        fireEventEntity.setCoords(fireEventDTO.getCoords());
    }
}