# This python program is designed to receive data from uBit through UART.
# It sends these data through MQTT to the Mosquitto Broker
# And it posts through HTTP to the WebServer in the Data Center
import json
import os
import sys

sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from core.config_utils import logger
from core.config_vars import MQTT_CLIENT_NAME
from core.uart import ser, init_uart
from service.uart_service import process_data_from_serial
from core.mqtt_client import MqttClient

if __name__ == "__main__":
    logger.info(f"Application {MQTT_CLIENT_NAME} started ! :) - Press Ctrl-C to quit")
    try:
        client = MqttClient(MQTT_CLIENT_NAME)
        # json_data = {
        #     "id": 4,
        #     "coords": {"latitude": 45.7710, "longitude": 4.8796},
        #     "intensity": 7.0,
        #     "start_date": "2024-01-12T18:30:00",
        #     "end_date": "2024-01-12T21:30:00",
        #     "real": True
        # }
        # client.publish_message("/manager/fire_event_finished", json.dumps(json_data))
        init_uart()
        process_data_from_serial()
    except (KeyboardInterrupt, SystemExit):
        ser.close()
        logger.info(f"Application {MQTT_CLIENT_NAME} is ending her life...")
        exit()
