package com.simulator;

import com.simulator.service.FireEventService;

public class Main {
    public static void main(String[] args) {
        FireEventService fireEventService = new FireEventService();
        fireEventService.createFire();
    }
}