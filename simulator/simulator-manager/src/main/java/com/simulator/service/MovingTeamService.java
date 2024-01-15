package com.simulator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.dto.InterventionMessageDTO;
import com.simulator.models.MovingTeamEntity;
import com.simulator.utils.LoggerUtil;
import com.simulator.utils.Topics;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovingTeamService {
    private static final Logger logger = LoggerUtil.getLogger();
    private static final MoveTeamsService moveTeamsService = new MoveTeamsService();


    static MQTTService mqttService = new MQTTService();
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public void sendTeamPosition(InterventionMessageDTO team) {
        try {
            String json = objectMapper.writeValueAsString(team);
            logger.info("Nouvelle position de team {}", json);
            mqttService.publish(Topics.getTopicName(Topics.SIMULATOR_TEAM_POSITION), json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTeam(MovingTeamEntity movingTeamEntity) {
       moveTeamsService.addTeam(movingTeamEntity);
    }
}
