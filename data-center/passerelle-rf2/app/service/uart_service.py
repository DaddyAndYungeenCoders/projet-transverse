import json
import os
import sys
from datetime import datetime

import serial

sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from core.config_utils import logger
from core.config_vars import DELIMITER
from core.uart import ser
from controller.controller import publish_fire_event, post_fire_event


def process_data_from_serial():
    buffer = b""
    # get data from serial
    while ser.is_open:
        if ser.in_waiting > 0:  # if incoming bytes are waiting
            # Read incoming data from Microbit
            data_new = ser.read(ser.in_waiting)
            # We use a buffer to store the data until we find the DELIMITER
            buffer += data_new
            while DELIMITER in buffer:
                # Extract data from buffer until DELIMITER
                message, buffer = buffer.split(DELIMITER, 1)
                message_str = message.decode('utf-8')

                logger.info("(SERIAL) received: " + message_str)
                fire_event_json = compute_uart_to_json(message_str)
                logger.info(f"Converted to JSON : {fire_event_json}")
                # fire_event_json = json.loads(message_str)

                # Publish fire_event to Broker
                publish_fire_event(json.dumps(fire_event_json))
                # POST fire_event to WebServer
                post_fire_event(fire_event_json)


def compute_uart_to_json(data):
    fire_event_id, intensity = extract_data_from_serial(data)
    return {"id": fire_event_id, "intensity": intensity, "timestamp": datetime.now().isoformat()}


def extract_data_from_serial(data):
    try:
        # Exemple de chaîne de données
        # id:0,in:11
        parts = data.split(',')

        fire_event_id = int(parts[0].split(':')[1])
        intensity = float(parts[1].split(':')[1])

        return fire_event_id, intensity
    # If there is an error while extracting data, we return -1, -1 to keep a trace
    except (ValueError, IndexError) as e:
        logger.info(f"Erreur lors de l'extraction des données: {e}")
        return -1, -1


def send_uart_message(msg):
    try:
        ser.write(msg.encode('utf-8'))
        logger.info("Message <" + msg + "> sent to micro-controller.")
    except serial.SerialException as e:
        logger.info("Error while sending message to micro-controller.")
        logger.info(e)
