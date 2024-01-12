package com.simulator.service;

import com.simulator.mqtt.MQTTClient;
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
    String clientName = "emergency_simulator";
    String brokerUrl = "127.0.0.1:1883";
    String topics_yaml = "config/mqtt_topics.yaml";
    Map<String, String> topics;
    MQTTClient mqttClient;

    public MQTTService(){
        try {
            mqttClient = MQTTClient.getClient(brokerUrl, clientName);
            if (!mqttClient.isConnected()){
                mqttClient.connectToBroker();
            }

            this.topics = mqttClient.loadTopicsFromConfig(topics_yaml);



        } catch (MqttException e) {
            // Gérer l'exception de manière appropriée (journalisation, notification, etc.)
            e.printStackTrace();
        }
    }

    public void publish(String topicName, String message){
        try {
            System.out.println("[MQTT publish] topic : " + topicName + " message : " + message);
            mqttClient.publishToBroker(getTopic(topicName), message);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTopic(String topicsName){
        return topics.get(topicsName);
    }


    public void subscribeToTopics(List<String> topicsName, IMqttMessageListener listener){
        try {
            List<String> listTopics = new ArrayList<>();
            topicsName.forEach((topicName) -> {
                listTopics.add(getTopic(topicName));
            });
            mqttClient.subscribeToTopics(listTopics, listener);
            System.out.println("Subscribe to topics" + listTopics);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public void deconnection(){
        try {
            mqttClient.disconnectFromBroker();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean isConnected(){
        return mqttClient.isConnected();
    }
}
