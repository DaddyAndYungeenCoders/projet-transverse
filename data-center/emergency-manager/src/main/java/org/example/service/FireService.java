package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.SensorDetection;
import org.example.utils.HttpUtils;
import org.example.utils.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class FireService {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    ObjectMapper mapper = new ObjectMapper();
    private static final String BASE_URL = "http://localhost:7777/api/fire-event";
    private static final PublishService pubService = PublishService.getPublishService();
    private static final TeamService teamService = new TeamService();

    private String urlApi(String endpoint) {
        return BASE_URL + endpoint;
    }

    public void handleFireConfirmation(String data) {
        try {
            SensorDetection sensorDetection = mapper.readValue(data, SensorDetection.class);
            if (sensorDetection.isReal()) {
                // if it's real, start intervention, and update fireEvent in webserver
                logger.info("Fire is Real !");
                // update reality of fire in any case
                updateFireEventValidationStatus(sensorDetection);
               teamService.processAvailableTeam(String.valueOf(sensorDetection.getId()));
            }

        } catch (Exception e) {
            logger.error("Error while decoding fireEventConfirmation : {}", String.valueOf(e));
        }
    }


    private HttpURLConnection setConnectionBaseParam(String endpoint, String method) throws Exception {
        URL url = new URI(urlApi(endpoint)).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        return connection;
    }

    public void updateFireEventValidationStatus(SensorDetection sensorDetection) {
        try {
            String sensorValuesAsJson = mapper.writeValueAsString(sensorDetection);
            HttpURLConnection connection =
                    setConnectionBaseParam("/update/" + sensorDetection.getId(), "PUT");

            HttpUtils.sendJson(connection, sensorValuesAsJson);
            String response = HttpUtils.readResponse(connection);

            logger.info("Manager WebServer responded with : {}", response);
        } catch (Exception e) {
           logger.error(e.getMessage());
        }
    }

    public void updateFireEventHandledStatus(String sensorId) {
        // update fireEvent by sensorId
        try {
            HttpURLConnection connection =
                    setConnectionBaseParam("/update-handled/" + sensorId, "PUT");

            HttpUtils.sendJson(connection, "");
            String response = HttpUtils.readResponse(connection);

            logger.info("Manager WebServer responded with : {}", response);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
