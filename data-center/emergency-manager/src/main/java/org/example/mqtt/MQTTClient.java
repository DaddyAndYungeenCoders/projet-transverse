package org.example.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public final class MQTTClient {
    private static MQTTClient client;
    public String brokerUrl;
    public String clientId;

     private MQTTClient(String brokerUrl, String clientId) throws MqttException {
         this.brokerUrl = brokerUrl;
         this.clientId = clientId;
     }

     public static MQTTClient getClient(String brokerUrl, String clientId) throws MqttException {
         if (client == null) {
             client = new MQTTClient(brokerUrl, clientId);
         }
         return client;
     }

     public void connect() throws MqttException {
         MqttConnectOptions options = new MqttConnectOptions();
         // options to define if needed
         char[] pw = "admin".toCharArray();
         String username = "admin";
         options.setUserName(username);
         options.setPassword(pw);

         client.connect();
     }

     public void disconnect() throws MqttException {
         client.disconnect();
     }

     public void publish(String topic, String message) throws MqttException {
         MqttMessage mqttMessage = new MqttMessage(message.getBytes());
         client.publish(topic, mqttMessage.toString());
     }

     public void subscribe(String topic, IMqttMessageListener listener) throws MqttException {
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
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFile)) {
            TopicsConfig config = yaml.loadAs(input, TopicsConfig.class);
            return config.getTopics();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading topics from config file.", e);
        }
    }
}
