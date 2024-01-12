import os
import sys

sys.path.append(os.path.dirname(os.path.abspath(__file__)))
from core.config_utils import logger
from core.config_vars import MQTT_CLIENT_NAME
from core.mqtt_client import MqttClient
from core.database_manager import DatabaseManager

if __name__ == '__main__':
    logger.info(f"Application {MQTT_CLIENT_NAME} started ! :) - Press Ctrl-C to quit")
    try:
        MqttClient(MQTT_CLIENT_NAME)
        try:
            db = DatabaseManager()
            logger.info(f"Successfully connected to database (Org: {db.org})")
        except BaseException as e:
            logger.error(f"It seems there was an error initializing Database : {e}")

        while True:
            pass

    except KeyboardInterrupt:
        logger.info(f"Application {MQTT_CLIENT_NAME} is ending her life...")
        exit()
