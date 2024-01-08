import logging
import os
import yaml
from dotenv import load_dotenv
from pydantic_settings import BaseSettings

load_dotenv()


# -------- TOPICS CONFIGURATION -------- #
def load_config(config_path, config_name):
    try:
        with open(config_path, 'r') as file:
            topics_yaml = yaml.safe_load(file)
            return topics_yaml.get(config_name, {})
    except yaml.YAMLError as e:
        print(f"Error loading YAML: {e}")


topics = load_config("app/config/topics.yaml", "topics")

# -------------------------------------- #


# -------- SETTINGS CONFIGURATION -------- #
class Settings(BaseSettings):
    app_name: str = "Relay service MQTT - WebServer API"
    debug: bool = False


settings = Settings()
# ---------------------------------------- #

# -------- LOGGER CONFIGURATION -------- #
logger = logging.getLogger()
logging.basicConfig(format='[%(levelname)s] %(asctime)s : %(message)s', datefmt='%m/%d/%Y %I:%M:%S %p')
logger.setLevel(logging.INFO)
# -------------------------------------- #


# -------- API CONFIGURATION -------- #
API_URL = os.getenv("API_URL") + ":" + os.getenv("API_PORT")
# ----------------------------------- #

# -------- MQTT CONFIGURATION -------- #
MQTT_CLIENT_NAME = "relay-mqtt-webserver"
FIRST_RECONNECT_DELAY = 1
RECONNECT_RATE = 2
MAX_RECONNECT_COUNT = 12
MAX_RECONNECT_DELAY = 60
MAX_SUB_RETRY = 30
QOS = 1
TOPICS_TO_SUBSCRIBE = [
    (topics.get("simulator.auto-fire-event"), QOS),
    (topics.get("simulator.new-sensor-value"), QOS)
]
FIRE_EVENT_VIEW_TOPIC = topics.get("simulator-view.fire-event")
SENSOR_CHANGE_VIEW_TOPIC = topics.get("simulator-view.sensor-changed")
# -------------------------------------- #
