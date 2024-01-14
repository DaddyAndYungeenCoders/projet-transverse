package com.simulator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.config.AppConfig;
import com.simulator.dto.FireEventDTO;
import com.simulator.models.FireEventEntity;
import com.simulator.models.SensorEntity;
import com.simulator.models.TeamEntity;
import com.simulator.models.database.entity.FireEventDatabaseEntity;
import com.simulator.utils.HttpUtils;
import com.simulator.utils.Topics;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class FireEventService {
    private static final String BASE_URL = "http://localhost:7777/api/fire-event";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    static SensorService sensorService = new SensorService();
    static HttpService httpService = new HttpService();
    static MQTTService mqttService = new MQTTService();

    public FireEventService() {
    }

    public static java.sql.Date convertUtilToSqlDate(java.util.Date utilDate) {
        if (utilDate == null) {
            throw new IllegalArgumentException("The provided date is null");
        }
        long timeInMillis = utilDate.getTime();
        return new java.sql.Date(timeInMillis);
    }

    public static void OnFireEvent(String message) {
        try {
            FireEventDTO fireEventDTO = objectMapper.readValue(message.toString(), FireEventDTO.class);
            FireEventEntity fireEvent = fireEventDTO.toEntity();
            System.out.println("fireEvent : " + fireEvent);
//                    List<SensorEntity> sensorEntities = httpService.getSensorEntities();
            String response = httpService.get(AppConfig.getWebServerURL() + "/api/sensor/fetch-all");
            System.out.println("Server Response : " + response);

            List<SensorEntity> sensorEntities = SensorService.convertJsonToSensorEntities(response);
            System.out.println(sensorEntities);
            SensorEntity nearestSensor = sensorService.findNearestSensor(sensorEntities, fireEvent.getCoords());
            nearestSensor.setIntensity(fireEvent.getRealIntensity());
            System.out.println("Nearest : " + nearestSensor.getId());
            String json = objectMapper.writeValueAsString(nearestSensor.toDTO());
            System.out.println("JSON sent to MQTT: " + json);
            mqttService.publish(Topics.getTopicName(Topics.SIMULATOR_NEW_SENSOR_VALUE), json);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle or log the exception
        }
    }

    private String urlApi(String endpoint) {
        return BASE_URL + endpoint;
    }

    private HttpURLConnection setConnectionBaseParam(String endpoint, String method) throws Exception {
        URL url = new URL(urlApi(endpoint));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        return connection;
    }

    public void createFire(FireEventEntity fireEvent) {
        // = new FireEventEntity(4L, new CoordsEntity(23.3, 23.1), 8, new Date(2024, 1,
        // 10), null, true);
        try {
            String jsonInputString = objectMapper.writeValueAsString(fireEvent);
            HttpURLConnection connection = setConnectionBaseParam("/create", "POST");

            HttpUtils.sendJson(connection, jsonInputString);
            String response = HttpUtils.readResponse(connection);

            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public FireEventEntity  updateFireIntensity(Integer intensity, FireEventEntity fireEvent) {
        try {
            System.out.println();
            String jsonInputString = objectMapper.writeValueAsString(fireEvent);
            HttpURLConnection connection = setConnectionBaseParam("/update/" + fireEvent.getId() + "/" + intensity, "PUT");

            HttpUtils.sendJson(connection, jsonInputString);
            String response = HttpUtils.readResponse(connection);
            System.out.println("CECI EST LA RESPONSE : " + response);
            FireEventDatabaseEntity updatedFireEvent = objectMapper.readValue(response,FireEventDatabaseEntity.class );
            System.out.println("LIGNE 97 :"+updatedFireEvent);
            return updatedFireEvent.toEntity();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateFire(FireEventEntity fireEvent) {
        try {
            String jsonInputString = objectMapper.writeValueAsString(fireEvent);
            HttpURLConnection connection = setConnectionBaseParam("/update/" + fireEvent.getId(), "PUT");

            HttpUtils.sendJson(connection, jsonInputString);
            String response = HttpUtils.readResponse(connection);

            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void fireRandomEvolution(FireEventEntity fireEvent) {
        if (!fireEvent.getFireState()) {
            Random rand = new Random();
            int upperbound = 10 - fireEvent.getRealIntensity();
            int int_random = rand.nextInt(upperbound);
            int booleanUpperBound = 2;
            int boolean_random = rand.nextInt(booleanUpperBound);
            int booleanFactor = boolean_random == 1 ? -1 : 1;
            int newIntensity = fireEvent.getRealIntensity() + booleanFactor * int_random;
            fireEvent.setRealIntensity(newIntensity);
            this.updateFireIntensity(newIntensity, fireEvent);

        }
    }

    public void reduceFire(TeamEntity team, FireEventEntity fireEvent) {
        while (fireEvent.getRealIntensity() > 0) {
            int newIntensity = Long.valueOf(fireEvent.getRealIntensity() * team.getFireMastery() / 10).intValue();
            fireEvent.setRealIntensity(newIntensity);
            this.updateFireIntensity(newIntensity, fireEvent);
        }
        fireEvent.setRealIntensity(0);
        fireEvent.setEndDate(convertUtilToSqlDate(new Date()));
        this.updateFire(fireEvent);
    }

    public void onReceiveInterventionEvent() {
        // Implementation
    }

    public void onReceiveActorEvent() {
        // Implementation
    }
}
