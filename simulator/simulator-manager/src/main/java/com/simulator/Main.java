package com.simulator;

import com.simulator.mqtt.MQTTClient;
import org.eclipse.paho.client.mqttv3.MqttException;


import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world22!");
//        SensorRepository sensorRepository = new SensorRepository();
//        System.out.println(SensorRepository.getSensorById(1L).getCoordsEntity().getLatitude());
//        FireEventRepository fireEventRepository = new FireEventRepository();
//        System.out.println(fireEventRepository.getFireEventById(2L).getCoords().getLatitude());
//        System.out.println(fireEventRepository.getFireEventById(2L).getIntervention().get(0).getDuration());
//        System.out.println(fireEventRepository.getFireEventById(2L).getIntervention().get(0).getTeam().getFire_station().getAddress());
//
//        System.out.println(SensorRepository.getSensorById(1L).getDetects().getFirst().getFireEventEntity().getIntervention().getFirst().getTeam().getFire_station().getAddress());
//
//        FireStationRepository fireStationRepository = new FireStationRepository();
//        System.out.println("test ulitme");
//        System.out.println(fireStationRepository.getFireStationById(1L).getTeams().get(0).getIntervention().get(0).getFire_event().getDetecs().getSensorEntity().getCoordsEntity().getLatitude());

        System.out.println("Emergency Simulator Started!");

        String clientName = "emergency_simulator";
        String brokerUrl = "127.0.0.1:1883";
        String topics_yaml = "config/mqtt_topics.yaml";
        try {
            MQTTClient mqttClient = MQTTClient.getClient(brokerUrl, clientName);
            mqttClient.connectToBroker();

            Map<String, String> topics = mqttClient.loadTopicsFromConfig(topics_yaml);

            mqttClient.publishToBroker(topics.get("manager.intervention"), "Intervention launched!");

            mqttClient.subscribeToTopicsFromConfig(topics_yaml, (topic, message) -> {
                System.out.println("Received message on topic " + topic + ": " + new String(message.getPayload()));
            });
            mqttClient.publishToBroker(topics.get("manager.intervention"), "Intervention launched!");
            while (mqttClient.isConnected()){

            }

            mqttClient.disconnectFromBroker();
        } catch (MqttException e) {
            // Gérer l'exception de manière appropriée (journalisation, notification, etc.)
            e.printStackTrace();
        }
    }
}