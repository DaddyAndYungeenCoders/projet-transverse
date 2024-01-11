import os
import sys
import json
from datetime import datetime
from dotenv import load_dotenv

sys.path.append(os.path.dirname(os.path.abspath(__file__)))
from core.config_utils import logger
from core.config_vars import MQTT_CLIENT_NAME
from core.mqtt_client import MqttClient
from service.database_service import init_influxdb_client

if __name__ == '__main__':
    logger.info(f"Application {MQTT_CLIENT_NAME} started ! :) - Press Ctrl-C to quit")
    try:
        MqttClient(MQTT_CLIENT_NAME)
        # db = init_influxdb_client()
        while True:
            pass

    except KeyboardInterrupt:
        logger.info(f"Application {MQTT_CLIENT_NAME} is ending her life...")
        exit()
