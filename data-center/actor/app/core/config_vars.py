import os

from dotenv import load_dotenv

from app.core.config_utils import load_config

load_dotenv()

# -------- API CONFIGURATION -------- #
API_URL = os.getenv("API_URL") + ":" + os.getenv("API_PORT") + "/api"
# ----------------------------------- #

# -------- MQTT CONFIGURATION -------- #
topics = load_config("app/config/topics.yaml", "topics")
MQTT_CLIENT_NAME = "actor-validation-service"
FIRST_RECONNECT_DELAY = 1
RECONNECT_RATE = 2
MAX_RECONNECT_COUNT = 12
MAX_RECONNECT_DELAY = 60
MAX_SUB_RETRY = 30
QOS = 1
ACTOR_VALIDATION_TOPIC = topics.get("actor.fire-validation")
TOPICS_TO_SUBSCRIBE = [
    (ACTOR_VALIDATION_TOPIC, QOS)
]

# -------------------------------------- #
