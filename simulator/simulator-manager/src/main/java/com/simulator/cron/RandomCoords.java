package com.simulator.cron;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.models.CoordsEntity;
import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.InputStream;
import java.util.stream.Collectors;


public class RandomCoords {
    GeometryFactory geometryFactory;
    Polygon polygon;
    ObjectMapper mapper = new ObjectMapper();
    Coordinate[] boundCoordinates;

    public RandomCoords() {
        try {
            String filePath = "src/main/java/com/simulator/cron/polygon.json";
            ObjectMapper objectMapper = new ObjectMapper();
            com.simulator.models.Polygon myObject = objectMapper.readValue(new File(filePath), com.simulator.models.Polygon.class);
            System.out.println(myObject.getCoordinates());

            this.boundCoordinates = myObject.getCoordinates().stream().map(coords -> new Coordinate(coords.get(1), coords.get(0))).toList().toArray(new Coordinate[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
