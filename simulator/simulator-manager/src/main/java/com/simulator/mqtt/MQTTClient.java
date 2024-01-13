package com.simulator.mqtt;

import com.simulator.utils.LoggerUtil;
import com.simulator.utils.Topics;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class MQTTClient extends MqttAsyncClient {
    private static final Logger logger = LoggerUtil.getLogger();
    private static final int MAX_RECONNECT_ATTEMPTS = 3;
    private static final int RECONNECT_DELAY_SECONDS = 3;
    private static final int QOS = 1;
    private static MQTTClient client;
    private int reconnectAttempts;
    private ScheduledExecutorService executorService;


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
                    String[] topicsArray = Topics.getTopicsArray();
                    int[] qosLevels = new int[topicsArray.length];
                    Arrays.fill(qosLevels, QOS);
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


}