import logging

import yaml
from pydantic_settings import BaseSettings


# -------- TOPICS CONFIGURATION -------- #
def load_config(config_path, config_name):
    try:
        with open(config_path, 'r') as file:
            topics_yaml = yaml.safe_load(file)
            return topics_yaml.get(config_name, {})
    except yaml.YAMLError as e:
        logger.error(f"Error loading YAML: {e}")


# -------------------------------------- #


# -------- SETTINGS CONFIGURATION -------- #
class Settings(BaseSettings):
    app_name: str = "Relay service MQTT - WebServer API"
    debug: bool = False


settings = Settings()
# ---------------------------------------- #

# -------- LOGGER CONFIGURATION -------- #
logger = logging.getLogger()
logging.basicConfig(format='[%(levelname)s] %(asctime)s : %(message)s', datefmt='%d/%m/%Y %I:%M:%S %p')
logger.setLevel(logging.INFO)
# -------------------------------------- #
