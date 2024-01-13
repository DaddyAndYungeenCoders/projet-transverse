package com.simulator.cron;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.models.CoordsEntity;
import com.simulator.models.FireEventEntity;
import com.simulator.service.FireEventService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Arrays;
import java.util.Date;

import java.util.Random;

public class CronJob implements Job {
    private GeometryFactory geometryFactory;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        RandomCoords randomCoords = new RandomCoords();

        this.geometryFactory = new GeometryFactory();

        System.out.println("CURRENT TRANSFORMATION");

        System.out.println(Arrays.toString(randomCoords.boundCoordinates));

        Polygon polygon = this.geometryFactory.createPolygon(new CoordinateArraySequence(randomCoords.boundCoordinates));

        System.out.println("CURRENT THIS.POLYGON");
        System.out.println(polygon);
        Random rand = new Random();
        int upperbound = 1;
        int int_random = rand.nextInt(upperbound);
        if (int_random == 0) {
            int upperboundRandomBoolean = 5;
            int randomBoolean = rand.nextInt(upperboundRandomBoolean);
            Boolean trueFire = randomBoolean ==0;
            int upperboundIntensity = 10;
            int intensity = 1+rand.nextInt(upperboundIntensity);
            System.out.println("GONNA GENERATE A FIRE");
            System.out.println("CRON EXECUTED;");
            FireEventService fireEventService = new FireEventService();
            CoordsEntity randomCoordinatesInBound = generateRandomCoordinateInsidePolygon(polygon);

            FireEventEntity fireEvent = new FireEventEntity(3L, new CoordsEntity(randomCoordinatesInBound.getLatitude(), randomCoordinatesInBound.getLongitude()), intensity, FireEventService.convertUtilToSqlDate(new Date()), null, trueFire);
            fireEventService.createFire(fireEvent);

        } else {
            System.out.println("!NOT GONNA GENERATE A FIRE!");
        }
    }

    public CoordsEntity generateRandomCoordinateInsidePolygon(Polygon polygon) {
        Random random = new Random();
        Envelope envelope = polygon.getEnvelopeInternal();

        while (true) {
            double x = envelope.getMinX() + (envelope.getMaxX() - envelope.getMinX()) * random.nextDouble();
            double y = envelope.getMinY() + (envelope.getMaxY() - envelope.getMinY()) * random.nextDouble();

            if (polygon.contains(this.geometryFactory.createPoint(new Coordinate(x, y)))) {
                return new CoordsEntity(x, y);
            }
        }
    }
}
