package org.example;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.example.mqtt.MQTTClient;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("Emergency Manager Started !");
        String clientName = "emergency_manager";
        String brokerUrl = "127.0.0.1:1883";
        String topics_yaml = "/config/topics.yaml";
        try {
            MQTTClient mqttClient = MQTTClient.getClient(brokerUrl, clientName);
            mqttClient.connect();

            Map<String, String> topics = mqttClient.loadTopicsFromConfig(topics_yaml);

            mqttClient.publish(topics.get("manager.intervention"), "intervention lancée !");

            mqttClient.subscribeToTopicsFromConfig("/config/mqtt_topics.yaml", (topic, message) -> {
                System.out.println("Received message on topic " + topic + ": " + new String(message.getPayload()));
            });

            mqttClient.disconnect();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}