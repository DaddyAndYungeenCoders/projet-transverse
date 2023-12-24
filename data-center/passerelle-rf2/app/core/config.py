import logging
import os

import yaml
from dotenv import load_dotenv

load_dotenv()


# -------- TOPICS CONFIGURATION -------- #
def load_config(config_path, config_name):
    try:
        with open(config_path, 'r') as file:
            topics_yaml = yaml.safe_load(file)
            return topics_yaml.get(config_name, {})
    except yaml.YAMLError as e:
        print(f"Error loading YAML: {e}")


topics = load_config("app/config/conf.yaml", "topics")
serial_conf = load_config("app/config/conf.yaml", "serial")

# -------------------------------------- #


# -------- LOGGER CONFIGURATION -------- #
logger = logging.getLogger()
logging.basicConfig(format='[%(levelname)s] %(asctime)s : %(message)s', datefmt='%m/%d/%Y %I:%M:%S %p')
logger.setLevel(logging.INFO)
# -------------------------------------- #


# -------- API CONFIGURATION -------- #
API_URL = os.getenv("API_URL") + ":" + os.getenv("API_PORT")
# ----------------------------------- #

# -------- MQTT CONFIGURATION -------- #
MQTT_CLIENT_NAME = "passerelle-rf2"
FIRST_RECONNECT_DELAY = 1
RECONNECT_RATE = 2
MAX_RECONNECT_COUNT = 12
MAX_RECONNECT_DELAY = 60
MAX_SUB_RETRY = 30
TOPIC_RF2_FIRE_EVENT = "rf2.fire_event"
# -------------------------------------- #
