package com.simulator.service;

import com.simulator.mqtt.MQTTClient;
import com.simulator.utils.LoggerUtil;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/* Exemple d'utilisation :

MQTTService mqttService = new MQTTService();
List<String> topicsToSubscribe = List.of("simulator-view.sensor-changed", "simulator-view.fire-event", "/simulator/auto_fire_event");
mqttService.subscribeToTopics(topicsToSubscribe, (topic, message) -> {
    if (Objects.equals(topic, mqttService.getTopic("simulator-view.sensor-changed"))){
        System.out.println("Received message on topic " + topic + ": " + new String(message.getPayload()));
    } else {
        System.out.println("Received message on topic inconnu" + topic + ": " + new String(message.getPayload()));

    }
});

mqttService.publish("simulator-view.sensor-changed", "YOLO");

while (mqttService.isConnected()){

}

mqttService.deconnection();



 */
public class MQTTService {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    String clientName = "emergency_simulator";
    String brokerUrl = "127.0.0.1:1883";
    MQTTClient mqttClient;

    public MQTTService() {
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