package com.simulator;

import com.simulator.mqtt.MQTTClient;
import com.simulator.repository.FireEventRepository;
import com.simulator.repository.FireStationRepository;
import com.simulator.repository.SensorRepository;
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