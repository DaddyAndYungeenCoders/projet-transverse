package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

public class InterventionService {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    ObjectMapper mapper = new ObjectMapper();
    private static final String BASE_URL = "http://localhost:7777/api";
    private static final PublishService pubService = PublishService.getPublishService();
    private static final FireService fireService = new FireService();
    static HttpService httpService = new HttpService();

    private String urlApi(String endpoint) {
        return BASE_URL + endpoint;
    }

    public void processAvailableTeam(String sensorId) {
        // get all teams fetch-all
        String response = null;
        response = httpService.get(BASE_URL + "/team/fetch-all");
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
                    String teamToSend = mapper.writeValueAsString(team);
                    pubService.pubManagerIntervention(teamToSend);
                    updateAvailabilityOfTeam(team);
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

    public void updateAvailabilityOfTeam(Team team) {
        try {
            String teamAsJson = mapper.writeValueAsString(team);
            HttpURLConnection connection =
                    setConnectionBaseParam("/update/" + team.getId(), "PUT");
            HttpUtils.sendJson(connection, teamAsJson);
            String response = HttpUtils.readResponse(connection);

            logger.info("Manager WebServer responded with : {}", response);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
