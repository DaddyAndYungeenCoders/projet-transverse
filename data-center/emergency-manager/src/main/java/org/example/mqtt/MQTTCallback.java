package org.example.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.example.models.SensorDetection;
import org.example.service.ActorService;
import org.example.service.FireService;
import org.example.utils.LoggerUtil;
import org.example.utils.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MQTTCallback implements MqttCallback {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    private final MQTTClient mqttClient;
    private static final ActorService actorService = ActorService.getActorService();
    private static final FireService fireService = new FireService();



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
            String data = new String(message.getPayload());
            logger.info("Received message from topic: {}", s);
            logger.info("Message: {}", data);
            if (s.equals(Topics.getTopicName(Topics.ACTOR_FIRE_VALIDATION))) {
                // received confirmation or whether fire is real or not.
                fireService.handleFireConfirmation(data);
            } else if (s.equals(Topics.getTopicName(Topics.RF2_FIRE_EVENT))) {
                // received new fire event, need to check if it's real or not
                actorService.checkIfFireIsReal(data);
            } else {
                // should not happen
                logger.error("Not a valid topic : {}", s);
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

