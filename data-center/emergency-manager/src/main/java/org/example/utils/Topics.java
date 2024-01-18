package org.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSException;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Topics {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    public static final String ACTOR_FIRE_VALIDATION = "actor.fire-validation";
    public static final String MANAGER_ASK_VALIDATION = "manager.ask-validation";
    public static final String MANAGER_FIRE_EVENT_FINISHED = "manager.finished-fire-event";
    public static final String MANAGER_INTERVENTION = "manager.intervention";
    public static final String RF2_FIRE_EVENT = "rf2.fire-event";
    public static final String SIM_TEAM_POSITION = "simulator.team-position";
    private static Map<String, String> topics;
    private static final String topics_path = "topics.yaml";
    private static final List<String> topicsToSubscribe = List.of(ACTOR_FIRE_VALIDATION, RF2_FIRE_EVENT, SIM_TEAM_POSITION);

    public void setTopics(Map<String, String> topics) {
        Topics.topics = topics;
    }

    public Map<String, String> getTopics() {
        return topics;
    }

    public static Map<String, String> loadTopicsFromConfig(String configFile) {
        Yaml yaml = new Yaml();
        Map<String, String> topics = null;

        try {
            InputStream input = Topics.class.getClassLoader().getResourceAsStream(configFile);
            if (input != null) {
                Topics config = yaml.loadAs(input, Topics.class);
                topics = config.getTopics();
            } else {
                // Gérer le cas où le fichier n'est pas trouvé
                System.err.println("Fichier de configuration introuvable: " + configFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors du chargement des sujets depuis le fichier de configuration.", e);
        }

        return topics;
    }

    public static String[] getTopicsArray() {
        topics = loadTopicsFromConfig(topics_path);
        List<String> listTopics = new ArrayList<>();
        topicsToSubscribe.forEach((topic) -> {
            listTopics.add(getTopicName(topic));
        });
        return listTopics.toArray(new String[0]);
    }

    public static String getTopicName(String topicsName) {
        return topics.get(topicsName);
    }
}