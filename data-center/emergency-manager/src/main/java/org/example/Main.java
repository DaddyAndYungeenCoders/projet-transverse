package org.example;

import org.example.service.MQTTService;
import org.example.utils.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
//    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    public static void main(String[] args) {
        logger.info("Emergency Manager Started !");

        MQTTService mqtt = MQTTService.getMqttService();

//        mqtt.publish("/rf2/test", "test");

    }

}