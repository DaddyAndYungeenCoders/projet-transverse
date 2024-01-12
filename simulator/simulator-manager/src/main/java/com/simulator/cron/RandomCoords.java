package com.simulator.cron;
import com.simulator.models.CoordsEntity;
import com.vividsolutions.jts.geom.*;

import java.util.Random;


public class RandomCoords {
    GeometryFactory geometryFactory;
    Polygon polygon;
    public RandomCoords(GeometryFactory geometryFactory,Polygon polygon){
        this.geometryFactory=geometryFactory;
        this.polygon=polygon;
    }
    public CoordsEntity generateRandomCoordinateInsidePolygon(Polygon polygon) {
        Random random = new Random();
        Envelope envelope = polygon.getEnvelopeInternal();

        while (true) {
            double x = envelope.getMinX() + (envelope.getMaxX() - envelope.getMinX()) * random.nextDouble();
            double y = envelope.getMinY() + (envelope.getMaxY() - envelope.getMinY()) * random.nextDouble();

            if (polygon.contains(geometryFactory.createPoint(new Coordinate(x, y)))) {
                return new CoordsEntity(x, y);
            }
        }
    }

}
