package com.simulator;

import com.simulator.cron.SchedulerManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) {
//        FireEventService fireEventService = new FireEventService();
//        FireEventEntity fireEvent = new FireEventEntity(3L, new CoordsEntity(10.3, 10.1), 8, FireEventService.convertUtilToSqlDate(new Date()) , null, false);
//        fireEventService.updateFireIntensity(1,fireEvent);
        try {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        SchedulerManager schedulerManager = new SchedulerManager();

        executorService.submit(schedulerManager::startScheduler);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}