package com.simulator;

import com.simulator.cron.SchedulerManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newSingleThreadExecutor();

            SchedulerManager schedulerManager = new SchedulerManager();

            executorService.submit(schedulerManager::startScheduler);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}