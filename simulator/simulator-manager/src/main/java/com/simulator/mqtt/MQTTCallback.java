package com.simulator.mqtt;

import com.simulator.service.FireEventService;
import com.simulator.utils.LoggerUtil;
import com.simulator.utils.Topics;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MQTTCallback implements MqttCallback {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
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
            if (s.equals(Topics.getTopicName(Topics.ACTOR_FIRE_VALIDATION))) {
                // received confirmation or whether fire is real or not.
                // if it's real, start intervention

                // if not, not doing anything special
            } else if (s.equals(Topics.getTopicName(Topics.RF2_FIRE_EVENT))) {
                // received new fire event, need to check if it's real or not
            } else if (s.equals(Topics.getTopicName(Topics.SIMULATOR_VIEW_FIRE_EVENT))) {
                FireEventService.OnFireEvent(new String(message.getPayload()));
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
