package org.example.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.example.models.Team;
import org.example.service.FireService;
import org.example.service.PublishService;
import org.example.service.TeamService;
import org.example.utils.LoggerUtil;
import org.example.utils.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MQTTCallback implements MqttCallback {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    private final MQTTClient mqttClient;
    private static final PublishService pubService = PublishService.getPublishService();
    private static final FireService fireService = new FireService();
    private static final TeamService teamService = new TeamService();
    ObjectMapper mapper = new ObjectMapper();


    public MQTTCallback(MQTTClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        logger.error("Connection to MQTT broker lost", throwable);
        mqttClient.attemptReconnect();
    }

    @Override
    public void messageArrived(String s, MqttMessage message) {
        try {
            String data = new String(message.getPayload());
            logger.info("Received message from topic: {}", s);
            logger.info("Message: {}", data);
            if (s.equals(Topics.getTopicName(Topics.ACTOR_FIRE_VALIDATION))) {
                // received confirmation or whether fire is real or not.
                // ajouter un thread peut etre pour gerer si aucune equipe dispo
                try {
//                    ExecutorService executorService = Executors.newCachedThreadPool();
//                    executorService.submit(movingTeamService.moveTeams());
                    fireService.handleFireConfirmation(data);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (s.equals(Topics.getTopicName(Topics.RF2_FIRE_EVENT))) {
                // received new fire event, need to check if it's real or not
                pubService.checkIfFireIsReal(data);
            } else if (s.equals(Topics.getTopicName(Topics.SIM_TEAM_POSITION))) {
                // received team position, post to DB
                teamService.updateTeamPosition(data);
            } else if (s.equals(Topics.getTopicName(Topics.MANAGER_FIRE_EVENT_FINISHED))) {
                // update availability of Team
                logger.info("Fire event is finished, updating availability of team");
                Team teamToUpdate = mapper.readValue(data, Team.class);
                teamService.updateAvailabilityOfTeam(teamToUpdate, true);
            } else {
                // should not happen
                logger.error("Not a valid topic : {}", s);
            }
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("Message succesfully published to topic : {}", (Object) iMqttDeliveryToken.getTopics());
    }

}

