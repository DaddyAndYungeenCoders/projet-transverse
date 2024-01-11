package com.simulator.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class MQTTClient extends MqttClient {
    private static MQTTClient client;
//    private final String brokerUrl;
//    private final String clientId;

    private MQTTClient(String brokerUrl, String clientId) throws MqttException {
        super("tcp://" + brokerUrl, clientId);
//        super(brokerUrl, clientId);
//        this.brokerUrl = brokerUrl;
//         this.clientId = clientId;
     }

     public static synchronized MQTTClient getClient(String brokerUrl, String clientId) throws MqttException {
         if (client == null) {
             client = new MQTTClient(brokerUrl, clientId);
         }
         return client;
     }

     public void connectToBroker() throws MqttException {
         MqttConnectOptions options = new MqttConnectOptions();
         // options to define if needed
         char[] pw = "admin".toCharArray();
         String username = "admin";
         options.setUserName(username);
         options.setPassword(pw);

         client.connect(options);
     }

     public void disconnectFromBroker() throws MqttException {
         client.disconnect();
     }

     public void publishToBroker(String topic, String message) throws MqttException {
         MqttMessage mqttMessage = new MqttMessage(message.getBytes());
         client.publish(topic, mqttMessage);
     }

     public void subscribeToBroker(String topic, IMqttMessageListener listener) throws MqttException {
         client.subscribe(topic, listener);
     }

    public void subscribeToTopics(List<String> topics, IMqttMessageListener listener) throws MqttException {
        for (String topic : topics) {
            client.subscribe(topic, listener);
        }
    }

    public void subscribeToTopicsFromConfig(String configFile, IMqttMessageListener listener) throws MqttException {
        Map<String, String> topicsMap = loadTopicsFromConfig(configFile);
        for (Map.Entry<String, String> entry : topicsMap.entrySet()) {
            String topicName = entry.getKey();
            String mqttTopic = entry.getValue();
            client.subscribe(mqttTopic, listener);
            System.out.println("Subscribed to topic " + mqttTopic);
        }
    }

    public Map<String, String> loadTopicsFromConfig(String configFile) {
        Yaml yaml = new Yaml();
        Map<String, String> topics = null;

        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream(configFile);
            if (input != null) {
                TopicsConfig config = yaml.loadAs(input, TopicsConfig.class);
                topics = config.getTopics();
            } else {
                // Gérer le cas où le fichier n'est pas trouvé
                System.err.println("Fichier de configuration introuvable: " + configFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors du chargement des sujets depuis le fichier de configuration.", e);
        }

        return topics;
    }





//    public String getBrokerUrl() {
//        return brokerUrl;
//    }
//
//    public String getClientId() {
//        return clientId;
//    }
}
