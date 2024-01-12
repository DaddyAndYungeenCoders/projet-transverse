package com.simulator.cron;

import com.simulator.models.CoordsEntity;
import com.simulator.models.FireEventEntity;
import com.simulator.service.FireEventService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class CronJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("CRON EXECUTED;");
        FireEventService fireEventService = new FireEventService();

        FireEventEntity fireEvent = new FireEventEntity(3L, new CoordsEntity(45.77994523235447,4.891641139984132 ), 8, FireEventService.convertUtilToSqlDate(new Date()) , null, false);
        fireEventService.createFire(fireEvent);
    }
}
