package com.simulator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.models.MovingTeamEntity;
import com.simulator.utils.LoggerUtil;
import com.simulator.utils.Topics;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MovingTeamService {
    private static List<MovingTeamEntity> teamsInIntervention =new ArrayList<>();
    private static final Logger logger = LoggerUtil.getLogger();


    static MQTTService mqttService = new MQTTService();
    private static final ObjectMapper objectMapper = new ObjectMapper();


   public void moveTeams(MovingTeamEntity team) {
       team.move();
       try {
           String json = objectMapper.writeValueAsString(team);
           logger.info("Nouvelle position de team {}", json);


           mqttService.publish(Topics.SIMULATOR_TEAM_POSITION, json);
       } catch (Exception e) {
           e.printStackTrace();
       }


   }


   public Runnable moveTeams() {
       while (true) {
           logger.info("Moving teams");
           for (MovingTeamEntity team : teamsInIntervention) {
               moveTeams(team);
           }
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
   }

    public void addTeam(MovingTeamEntity movingTeamEntity) {
       teamsInIntervention.add(movingTeamEntity);
    }
}
