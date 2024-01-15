import os
import sys

sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from dotenv import load_dotenv

from core.config_utils import load_config

load_dotenv()

# -------- API CONFIGURATION -------- #
API_URL = os.getenv("API_URL") + ":" + os.getenv("API_PORT") + "/api"
# ----------------------------------- #

# -------- MQTT CONFIGURATION -------- #
topics = load_config("app/config/topics.yaml", "topics")
MQTT_CLIENT_NAME = "passerelle-rf2"
FIRST_RECONNECT_DELAY = 1
RECONNECT_RATE = 2
MAX_RECONNECT_COUNT = 12
MAX_RECONNECT_DELAY = 60
MAX_SUB_RETRY = 30
QOS = 1
RF2_FIRE_EVENT_TOPIC = topics.get("rf2.fire-event")
TOPICS_TO_SUBSCRIBE = [
]

# -------------------------------------- #
# -------- SERIAL CONFIGURATION -------- #
SERIALPORT = "/dev/ttyACM0"
BAUDRATE = 115200
DELIMITER = b"|"
# ----------------------------------- #
