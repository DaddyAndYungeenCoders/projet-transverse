package org.example.utils;

import java.util.Map;

public class Topics {
    public static final String ACTOR_FIRE_VALIDATION = "actor.fire-validation";
    public static final String MANAGER_ASK_VALIDATION = "manager.ask-validation";
    public static final String MANAGER_FIRE_EVENT_FINISHED = "manager.fire-event-finished";
    public static final String MANAGER_INTERVENTION = "manager.intervention";
    public static final String RF2_FIRE_EVENT = "rf2.fire_event";
    private Map<String, String> topics;

    public Map<String, String> getTopics() {
        return topics;
    }

    public void setTopics(Map<String, String> topics) {
        this.topics = topics;
    }
}