package com.simulator.cron;

import com.simulator.models.CoordsEntity;
import com.simulator.models.FireEventEntity;
import com.simulator.service.FireEventService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.Random;

public class CronJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        Random rand = new Random();
        int upperbound = 1;
        int int_random = rand.nextInt(upperbound);
        if(int_random==0){
            System.out.println("GONNA GENERATE A FIRE");
        System.out.println("CRON EXECUTED;");
        FireEventService fireEventService = new FireEventService();
        double minLat = 45.77994523233440;
        double maxLat = 45.77994523238450;

        Random random = new Random();
        double randomLat = minLat + (maxLat - minLat) * random.nextDouble();

        double minLong = 4.891641139981130;
        double maxLong = 4.891641139989140;
        double randomLong = minLong + (maxLong - minLong) * random.nextDouble();

        FireEventEntity fireEvent = new FireEventEntity(3L, new CoordsEntity(randomLat,randomLong ), 8, FireEventService.convertUtilToSqlDate(new Date()) , null, false);
        fireEventService.createFire(fireEvent);

        }else{

            System.out.println("!NOT GONNA GENERATE A FIRE!");
        }
    }
}
