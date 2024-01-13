package org.example.service;

import org.example.mqtt.MQTTClient;
import org.example.utils.LoggerUtil;
import org.example.utils.Topics;
import org.slf4j.Logger;

public class ActorService {
    private static final Logger logger = LoggerUtil.getLogger();
    private static ActorService actorService;
    private static MQTTService mqtt = MQTTService.getMqttService();

    private ActorService() {
    }

    public static ActorService getActorService() {
        if (actorService == null) {
            actorService = new ActorService();
        }
        return actorService;
    }

    public void checkIfFireIsReal(String payload) {
        logger.info("Asking Actor to check fire reality...");
        logger.info(payload);
//        mqtt.publish(Topics.MANAGER_ASK_VALIDATION, payload);
    }


}
