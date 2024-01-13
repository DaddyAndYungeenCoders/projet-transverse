package org.example.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.example.utils.LoggerUtil;
import org.example.utils.Topics;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class MQTTClient extends MqttAsyncClient {
    private static final Logger logger = LoggerUtil.getLogger();
    private static final int MAX_RECONNECT_ATTEMPTS = 3;
    private static final int RECONNECT_DELAY_SECONDS = 3;

    private int reconnectAttempts;
    private ScheduledExecutorService executorService;
    private static MQTTClient client;
    private static final String topics_path = "topics.yaml";
    private static final List<String> topicsToSubscribe =
            List.of(Topics.ACTOR_FIRE_VALIDATION,
                    Topics.RF2_FIRE_EVENT);

    Map<String, String> topics;
    int qos = 1;

    private MQTTClient(String brokerUrl, String clientId) throws MqttException {
        super("tcp://" + brokerUrl, clientId, new MemoryPersistence());
        MQTTCallback callback = new MQTTCallback(this);
        setCallback(callback);
        this.reconnectAttempts = 0;
        this.executorService = Executors.newSingleThreadScheduledExecutor();

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

        client.connect(options, null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                logger.info("Successfully connected to Broker at {}", client.getServerURI());
                try {
                    String[] topicsArray = getTopicsArray();
                    int[] qosLevels = new int[topicsArray.length];
                    Arrays.fill(qosLevels, qos);
                    IMqttToken token = subscribe(topicsArray, qosLevels);
                    logger.info("Successfully subscribed to topics {}", (Object) topicsArray);
                    resetScheduler();
                } catch (MqttException e) {
                    logger.error("There was an error subscribing to topics.", e);
                }
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable exception) {
                logger.error("Failed to connect to MQTT broker", exception);
                if (reconnectAttempts < MAX_RECONNECT_ATTEMPTS) {
                    logger.info("Scheduling reconnection attempt in {} seconds...", RECONNECT_DELAY_SECONDS);
                    executorService.schedule(this::attemptReconnect, RECONNECT_DELAY_SECONDS, TimeUnit.SECONDS);
                } else {
                    logger.error("Max reconnect attempts reached. Giving up.");
                }
            }

            private void attemptReconnect() {
                client.attemptReconnect();
            }

            private void resetScheduler() {
                reconnectAttempts = 0;
                executorService.shutdown();
                executorService = Executors.newSingleThreadScheduledExecutor();
            }
        });
    }

    private String[] getTopicsArray() {
        topics = loadTopicsFromConfig(topics_path);
        List<String> listTopics = new ArrayList<>();
        topicsToSubscribe.forEach((topic) -> {
            listTopics.add(getTopicName(topic));
        });

        return listTopics.toArray(new String[0]);
    }

    public void attemptReconnect() {
        reconnectAttempts++;
        logger.info("Attempting reconnect (Attempt {}/{})", reconnectAttempts, MAX_RECONNECT_ATTEMPTS);

        try {
            connectToBroker();
        } catch (MqttException e) {
            logger.error("Reconnect attempt failed.", e);
        }
    }


    public void disconnectFromBroker() throws MqttException {
        executorService.shutdown();
        client.disconnect();
    }

    public void publishToBroker(String topic, String message) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        client.publish(topic, mqttMessage);
    }

    public Map<String, String> loadTopicsFromConfig(String configFile) {
        Yaml yaml = new Yaml();
        Map<String, String> topics = null;

        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream(configFile);
            if (input != null) {
                Topics config = yaml.loadAs(input, Topics.class);
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

    public String getTopicName(String topicsName) {
        return topics.get(topicsName);
    }
}