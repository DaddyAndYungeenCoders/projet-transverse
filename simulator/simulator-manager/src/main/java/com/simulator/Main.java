package com.simulator;

import com.simulator.repository.SensorRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world22!");
        SensorRepository sensorRepository = new SensorRepository();
        System.out.println(sensorRepository.getSensorById(1).getCoordsEntity().getLatitude());
    }
}