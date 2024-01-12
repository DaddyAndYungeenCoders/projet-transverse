package com.simulator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.models.CoordsEntity;
import com.simulator.models.FireEventEntity;
import com.simulator.utils.HttpUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Date;

public class FireEventService {
    private static final String BASE_URL = "http://localhost:7777/api/fire-event";

    // ObjectMapper is thread-safe and reusable. Creating it once and reusing it.
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public FireEventService() {}

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
//         = new FireEventEntity(4L, new CoordsEntity(23.3, 23.1), 8, new Date(2024, 1, 10), null, true);
        try {
            String jsonInputString = MAPPER.writeValueAsString(fireEvent);
            HttpURLConnection connection = setConnectionBaseParam("/create","POST");

            HttpUtils.sendJson(connection, jsonInputString);
            String response = HttpUtils.readResponse(connection);

            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void updateFireIntensity(Integer intensity, FireEventEntity fireEvent) {
        try {
            String jsonInputString = MAPPER.writeValueAsString(fireEvent);
//            HttpURLConnection connection = setConnectionBaseParam("/update/"+fireEvent.getId(),"PUT");
            HttpURLConnection connection = setConnectionBaseParam("/update/"+fireEvent.getId()+"/"+intensity,"PUT");

            HttpUtils.sendJson(connection, jsonInputString);
            String response = HttpUtils.readResponse(connection);

            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateFire( FireEventEntity fireEvent) {
        try {
            String jsonInputString = MAPPER.writeValueAsString(fireEvent);
            HttpURLConnection connection = setConnectionBaseParam("/update/"+fireEvent.getId(),"PUT");

            HttpUtils.sendJson(connection, jsonInputString);
            String response = HttpUtils.readResponse(connection);

            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void onReceiveInterventionEvent() {
        // Implementation
    }

    public void onReceiveActorEvent() {
        // Implementation
    }
    public static java.sql.Date convertUtilToSqlDate(java.util.Date utilDate) {
        if (utilDate == null) {
            throw new IllegalArgumentException("The provided date is null");
        }
        long timeInMillis = utilDate.getTime();
        return new java.sql.Date(timeInMillis);
    }
}
