import json
from typing import Dict

import serial

from app.core.config import serial_conf, MQTT_CLIENT_NAME, logger
from app.core.mqtt_client import MqttClient

ser = serial.Serial()
client = MqttClient(MQTT_CLIENT_NAME)


def init_uart():
    ser.port = serial_conf.get("serial_port")
    ser.baudrate = serial_conf.get("baudrate")
    ser.bytesize = serial.EIGHTBITS  # number of bits per bytes
    ser.parity = serial.PARITY_NONE  # set parity check: no parity
    ser.stopbits = serial.STOPBITS_ONE  # number of stop bits
    ser.timeout = None  # block read
    # ser.timeout = 0             #non-block read
    # ser.timeout = 2              #timeout block read
    ser.xonxoff = False  # disable software flow control
    ser.rtscts = False  # disable hardware (RTS/CTS) flow control
    ser.dsrdtr = False  # disable hardware (DSR/DTR) flow control
    # ser.writeTimeout = 0     # timeout for write
    print('Starting Up Serial Monitor')
    print('Press Ctrl-C to quit.')
    try:
        ser.open()
    except serial.SerialException:
        print("Serial {} port not available".format(ser.port))
        exit()


def process_serial_data():
    buffer = b""
    delimiter = b"|"
    while ser.is_open:
        if ser.in_waiting > 0:  # if incoming bytes are waiting
            # Read incoming data from Microbit
            data_new = ser.read(ser.in_waiting)
            # We use a buffer to store the data until we find the delimiter
            buffer += data_new
            while delimiter in buffer:
                # Extract data from buffer until delimiter
                message, buffer = buffer.split(delimiter, 1)
                message_str = message.decode()

                logger.info("(SERIAL) received: " + message_str)

                # send data to MQTT topic
                fire_event = extract_data_from_serial(message_str)
                fire_event_json = json.dumps(fire_event)
                print(f"id: {fire_event['id']}, intensity: {fire_event['intensity']}")
                client.publish_new_fire_event(fire_event_json)

                # post data to webserver
                # endpoint = url + "/newFireEvent"
                # x = requests.post(endpoint, json=fire_event_json)


def extract_data_from_serial(data) -> dict[str, int]:
    try:
        # Exemple de chaîne de données
        # id :2,intensity :1
        parts = data.split(',')
        print("parts" + str(parts))

        fire_event_id = int(parts[0].split(':')[1])
        intensity = float(parts[1].split(':')[1])

        return {"id": fire_event_id, "intensity": intensity}
    # If there is an error while extracting data, we return -1, -1  to keep a trace
    except (ValueError, KeyError, IndexError) as e:
        print(f"Erreur lors de l'extraction des données: {e}")
        return {"id": -1, "intensity": -1}
