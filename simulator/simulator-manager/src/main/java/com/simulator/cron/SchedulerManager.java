package com.simulator.cron;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Random;

public class SchedulerManager {
    public void startScheduler() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            JobDetail job = JobBuilder.newJob(CronJob.class)
                    .withIdentity("cronJob", "cronGroup")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger", "cronGroup")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ? *")) // Cron expression for every minute
                    .build();


            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

}
