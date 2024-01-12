# This python program is designed to receive data from uBit through UART.
# It sends these data through MQTT to the Mosquitto Broker
# And it posts through HTTP to the WebServer in the Data Center
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
        init_uart()
        process_data_from_serial()
    except (KeyboardInterrupt, SystemExit):
        ser.close()
        logger.info(f"Application {MQTT_CLIENT_NAME} is ending her life...")
        exit()
