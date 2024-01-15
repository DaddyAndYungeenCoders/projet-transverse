package com.simulator;

import com.simulator.cron.SchedulerManager;
import com.simulator.service.FireEventService;
import com.simulator.service.MoveTeamsService;
import com.simulator.service.MovingTeamService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static MovingTeamService movingTeamService = new MovingTeamService();
    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();

            SchedulerManager schedulerManager = new SchedulerManager();
            executorService.submit(schedulerManager::startScheduler);

//            executorService.submit(movingTeamService.moveTeams(executorService));
            MoveTeamsService moveTeamsService = new MoveTeamsService();
            Thread t = new Thread(moveTeamsService);
            t.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        new FireEventService();
    }
}