package org.example.service;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.example.mqtt.MQTTClient;
import org.example.utils.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class MQTTService {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    private static final String clientName = "emergency-manager";
    private static final String brokerUrl = "127.0.0.1:1883";
    private static MQTTClient mqttClient;
    private static MQTTService mqttService;

    private MQTTService() {
        try {
            mqttClient = MQTTClient.getClient(brokerUrl, clientName);
            if (!mqttClient.isConnected()) {
                mqttClient.connectToBroker();
            }
            while (!mqttClient.isConnected()) {
                Thread.sleep(10);
            }
        } catch (MqttException e) {
            logger.error("There was an error getting the client...");
            logger.error(String.valueOf(e));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static MQTTService getMqttService() {
        if (mqttService == null) {
            mqttService = new MQTTService();
        }
        return mqttService;
    }

    public void publish(String topicName, String message) {
        try {
            mqttClient.publishToBroker(topicName, message);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void deconnection() {
        try {
            mqttClient.disconnectFromBroker();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
}
