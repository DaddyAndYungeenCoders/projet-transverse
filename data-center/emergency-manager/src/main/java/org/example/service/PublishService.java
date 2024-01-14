package org.example.service;

import org.example.utils.LoggerUtil;
import org.example.utils.Topics;
import org.slf4j.Logger;

public class PublishService {
    private static final Logger logger = LoggerUtil.getLogger();
    private static PublishService publishService;
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
        mqtt.publish(Topics.getTopicName(Topics.getTopicName(Topics.MANAGER_ASK_VALIDATION)), payload);
    }

    public void pubManagerIntervention(String payload) {
        logger.info("Publishing a new Manager Intervention. Team is : {}", payload);
        mqtt.publish(Topics.getTopicName(Topics.getTopicName(Topics.MANAGER_INTERVENTION)), payload);
    }


}
