package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.SensorDetection;
import org.example.utils.LoggerUtil;
import org.example.utils.Topics;
import org.slf4j.Logger;

public class PublishService {
    private static final Logger logger = LoggerUtil.getLogger();
    private static PublishService publishService;
    ObjectMapper mapper = new ObjectMapper();
    private static MQTTService mqtt = MQTTService.getMqttService();

    private PublishService() {
    }

    public static PublishService getPublishService() {
        if (publishService == null) {
            publishService = new PublishService();
        }
        return publishService;
    }

    public void checkIfFireIsReal(String payload) {
        logger.info("Asking Actor to check fire reality with : {}", payload);
//        try {
//            SensorDetection sensorDetection = mapper.readValue(payload, SensorDetection.class);
//            if (sensorDetection.getIntensity() > 0) {
                mqtt.publish(Topics.getTopicName(Topics.MANAGER_ASK_VALIDATION), payload);
//            }
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        };
    }

    public void pubManagerIntervention(String payload) {
        logger.info("Publishing a new Manager Intervention. Team is : {}", payload);
        mqtt.publish(Topics.getTopicName(Topics.MANAGER_INTERVENTION), payload);
    }
}
