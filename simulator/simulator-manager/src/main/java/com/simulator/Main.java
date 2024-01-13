package com.simulator;

import com.simulator.mqtt.MQTTClient;
import com.simulator.service.FireEventService;
import com.simulator.service.MQTTService;
import org.eclipse.paho.client.mqttv3.MqttException;


import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("Emergency Simulator Started!");

        FireEventService fireEventService = new FireEventService();
    }
}