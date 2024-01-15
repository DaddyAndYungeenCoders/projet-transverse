package com.simulator.service;

import com.simulator.models.MovingTeamEntity;
import com.simulator.utils.LoggerUtil;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MoveTeamsService implements Runnable {
    private static List<MovingTeamEntity> teamsInIntervention = new ArrayList<>();
    private static final Logger logger = LoggerUtil.getLogger();
    public void run() {
        while (true) {
            logger.info("Moving teams");
            List<MovingTeamEntity> teamsCopy = new ArrayList<>(teamsInIntervention);

            for (MovingTeamEntity team : teamsCopy) {
                if (team != null) {
                    MoveTeamService moveTeamService = new MoveTeamService(team);
                    Thread t = new Thread(moveTeamService);
                    t.start();
                    if (team.isBackHome()) {
                        teamsInIntervention.remove(team);
                    }
                }
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
