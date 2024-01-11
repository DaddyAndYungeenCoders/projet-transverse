import os

from dotenv import load_dotenv

from app.core.config_utils import load_config

load_dotenv()

# -------- API CONFIGURATION -------- #
API_URL = os.getenv("API_URL") + ":" + os.getenv("API_PORT")
NEW_AUTO_FIRE = "api/fire-event/create"
NEW_SENSOR_VALUES = "api/sensor/update/%s"
# ----------------------------------- #

# -------- MQTT CONFIGURATION -------- #
topics = load_config("app/config/topics.yaml", "topics")
MQTT_CLIENT_NAME = "relay-mqtt-webserver"
FIRST_RECONNECT_DELAY = 1
RECONNECT_RATE = 2
MAX_RECONNECT_COUNT = 12
MAX_RECONNECT_DELAY = 60
MAX_SUB_RETRY = 30
QOS = 1
FIRE_EVENT_VIEW_TOPIC = topics.get("simulator-view.fire-event")
SENSOR_CHANGE_VIEW_TOPIC = topics.get("simulator-view.sensor-changed")
AUTO_EVENT_SIM_TOPIC = topics.get("simulator.auto-fire-event")
NEW_SENSOR_VALUES_TOPIC = topics.get("simulator.new-sensor-value")

TOPICS_TO_SUBSCRIBE = [
    (AUTO_EVENT_SIM_TOPIC, QOS),
    (NEW_SENSOR_VALUES_TOPIC, QOS)
]
# -------------------------------------- #
