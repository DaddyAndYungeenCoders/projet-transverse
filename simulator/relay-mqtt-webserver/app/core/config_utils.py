import logging
from logging.handlers import TimedRotatingFileHandler

import yaml
from pydantic_settings import BaseSettings


# -------- SETTINGS CONFIGURATION -------- #
class Settings(BaseSettings):
    app_name: str = "Relay service MQTT - WebServer API"
    debug: bool = False


settings = Settings()
# ---------------------------------------- #

# -------- LOGGER CONFIGURATION -------- #
logger = logging.getLogger()
logger.setLevel(logging.INFO)
formatter = logging.Formatter(fmt='[%(levelname)s] %(asctime)s : %(message)s', datefmt='%d/%m/%Y %I:%M:%S %p')

file_handler = TimedRotatingFileHandler('logs/simu-relay-mqtt-api.log', when='D', interval=1, backupCount=10)
file_handler.setFormatter(formatter)
logger.addHandler(file_handler)

console_handler = logging.StreamHandler()
console_handler.setFormatter(formatter)
logger.addHandler(console_handler)


# -------------------------------------- #
# -------- TOPICS CONFIGURATION -------- #
def load_config(config_path, config_name):
    try:
        with open(config_path, 'r') as file:
            topics_yaml = yaml.safe_load(file)
            return topics_yaml.get(config_name, {})
    except yaml.YAMLError as e:
        logger.error(f"Error loading YAML: {e}")

# -------------------------------------- #
