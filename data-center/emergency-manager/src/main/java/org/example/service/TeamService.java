package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Coords;
import org.example.models.InterventionMessageDTO;
import org.example.models.SensorEntity;
import org.example.models.Team;
import org.example.utils.HttpUtils;
import org.example.utils.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public class TeamService {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    ObjectMapper mapper = new ObjectMapper();
    private static final String BASE_URL = "http://localhost:8080/api/team";
    private static final String BASE_URL_SENSOR = "http://localhost:8080/api/sensor";

    private static final PublishService pubService = PublishService.getPublishService();
    private static final FireService fireService = new FireService();
    static HttpService httpService = new HttpService();

    private String urlApi(String endpoint) {
        return BASE_URL + endpoint;
    }

    public void processAvailableTeam(String sensorId) {
        // get all teams fetch-all
        String response = null;
        response = HttpService.get(BASE_URL + "/fetch-all");
        logger.info(response);
        logger.info("Getting all teams...");
        try {
            List<Team> allTeams = mapper.readValue(response, new TypeReference<List<Team>>() {
            });
            // find first available teams
            Optional<Team> firstAvailableTeam = allTeams.stream()
                    .filter(Team::isAvailable)
                    .findFirst();

            // publish message team inter + put team inter to webserver + update fireEvent to isHandled in simulator
            firstAvailableTeam.ifPresentOrElse(team -> {
                try {
                    logger.info("Available team found : {}", team);
                    String responseSensor = HttpService.get(BASE_URL_SENSOR + "/get/" + sensorId);
                    logger.info("Getting sensor..." + responseSensor);
                    SensorEntity sensor = mapper.readValue(responseSensor, SensorEntity.class);
                    InterventionMessageDTO newIntervention = new InterventionMessageDTO(sensor.getCoords(), team.getStamina(), 10, team.getFireStationId(), team.getId(), sensor.getId());
                    String interventionTosend = mapper.writeValueAsString(newIntervention);
                    pubService.pubManagerIntervention(interventionTosend);

                    updateAvailabilityOfTeam(team, false);
                    fireService.updateFireEventHandledStatus(sensorId);

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }, () -> {
                logger.warn("Aucune équipe de disponible...");
            });
        } catch (Exception e) {
            logger.error("There was an error decoding Team : {}", e.getMessage());
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

    public void updateAvailabilityOfTeam(Team team, boolean availability) {
        try {
            team.setAvailable(availability);
            String teamAsJson = mapper.writeValueAsString(team);
            HttpURLConnection connection =
                    setConnectionBaseParam("/available/" + team.getId() + "/" + availability , "PUT");
            HttpUtils.sendJson(connection, teamAsJson);
            String response = HttpUtils.readResponse(connection);

            logger.info("Manager WebServer responded with : {}", response);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updateTeamPosition(String data) {
        try {
            Team team = mapper.readValue(data, Team.class);
            Coords newCoords = team.getCoords();
            String newCoordsAsJson = mapper.writeValueAsString(newCoords);
            HttpURLConnection connection =
                    setConnectionBaseParam("/coords/" + team.getId(), "PUT");
            HttpUtils.sendJson(connection, newCoordsAsJson);
            String response = HttpUtils.readResponse(connection);

            logger.info("Manager WebServer responded with : {}", response);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
