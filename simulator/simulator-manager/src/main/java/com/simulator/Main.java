package com.simulator;

import com.simulator.models.CoordsEntity;
import com.simulator.models.FireEventEntity;
import com.simulator.service.FireEventService;

import java.util.Date;


public class Main {
    public static java.sql.Date convertUtilToSqlDate(Date utilDate) {
        if (utilDate == null) {
            throw new IllegalArgumentException("The provided date is null");
        }
        long timeInMillis = utilDate.getTime();
        return new java.sql.Date(timeInMillis);
    }
    public static void main(String[] args) {
        FireEventService fireEventService = new FireEventService();
        FireEventEntity fireEvent = new FireEventEntity(3L, new CoordsEntity(10.3, 10.1), 8,convertUtilToSqlDate(new Date()) , null, false);
//        fireEventService.createFire(fireEvent)
        fireEventService.updateFireIntensity(1,fireEvent);
    }
}