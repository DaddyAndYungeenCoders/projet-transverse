package org.example.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.example.utils.LoggerUtil;
import org.example.utils.Topics;
import org.slf4j.Logger;

public class MQTTCallback implements MqttCallback {
    private static final Logger logger = LoggerUtil.getLogger();
    private final MQTTClient mqttClient;


    public MQTTCallback(MQTTClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        logger.error("Connection to MQTT broker lost", throwable);
        mqttClient.attemptReconnect();
    }

    @Override
    public void messageArrived(String s, MqttMessage message) {
        try {
            logger.info("Received message from topic: {}", s);
            logger.info("Message: {}", new String(message.getPayload()));
            if (s.equals(Topics.ACTOR_FIRE_VALIDATION)) {
                // call appropiraite method
            } else if (s.equals(Topics.RF2_FIRE_EVENT)) {
                // call other func
            } else {
                // should not happen
                logger.error("Not a valid topic");
            }
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("Message succesfully published to topic : {}", (Object) iMqttDeliveryToken.getTopics());
    }

}

