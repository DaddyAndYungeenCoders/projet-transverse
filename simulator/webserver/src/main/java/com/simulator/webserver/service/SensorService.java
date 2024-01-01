package com.simulator.webserver.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.webserver.models.SensorEntity;
import com.simulator.webserver.repository.SensorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class SensorService {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private ObjectMapper objectMapper = new ObjectMapper();

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository){
        headers.setContentType(MediaType.APPLICATION_JSON);
        this.sensorRepository = sensorRepository;
    }


    public SensorEntity getSensor(Long id){
      return this.sensorRepository.findById(id).orElse(null);
    }

    public SensorEntity saveSensor(SensorEntity sensorEntity){
      return this.sensorRepository.save(sensorEntity);
    }

    public void deleteSensor(Long id){
      this.sensorRepository.deleteById(id);
    }
    public List<SensorEntity> findAll(){
        return sensorRepository.findAll();
    }
}
