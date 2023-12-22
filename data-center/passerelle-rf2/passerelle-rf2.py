# This python program is designed to receive data from uBit through UART.
# It sends these data through MQTT to the Mosquitto Broker
# And it posts through HTTP to the WebServer in the Data Center
import os
import time
import serial
import requests
from dotenv import load_dotenv
from datetime import datetime
import json

from shared.project_utils import load_config, init_mqtt_broker

# send serial message
SERIALPORT = "/dev/ttyACM0"
BAUDRATE = 115200
ser = serial.Serial()

client_name = "passerelle-rf2"
topics_path = "shared/config/topics.yaml"
topic_rf2_fire_event = "rf2.fire_event"
url = os.getenv("API_URL")


def init_uart():
    ser.port = SERIALPORT
    ser.baudrate = BAUDRATE
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
    try:
        ser.open()
    except serial.SerialException:
        print("Serial {} port not available".format(SERIALPORT))
        exit()


def extract_data_from_serial(data):
    try:
        # Exemple de chaîne de données
        # id :2,intensity :1
        parts = data.split(',')
        print("parts" + str(parts))

        id = int(parts[0].split(':')[1])
        intensity = float(parts[1].split(':')[1])

        return {"id": id, "intensity": intensity}
    # If there is an error while extracting data, we return -1, -1  to keep a trace
    except (ValueError, KeyError, IndexError) as e:
        print(f"Erreur lors de l'extraction des données: {e}")
        return {"id": -1, "intensity": -1}


def main():
    # Init UART connection to microbit
    init_uart()

    print('Press Ctrl-C to quit.')

    buffer = b""
    delimiter = b"|"
    a = 0
    try:
        # get data from serial
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

                    print("(SERIAL) received: " + message_str)

                    # send data to MQTT topic
                    fire_event_json = extract_data_from_serial(message_str)
                    print(f"id: {fire_event_json['id']}, intensity: {fire_event_json['intensity']}")
                    client.publish(topics.get(topic_rf2_fire_event), json.dumps(fire_event_json))
                    print(f"Publish to {topics.get(topic_rf2_fire_event)} : {json.dumps(fire_event_json)}")

                    # post data to webserver
                    # endpoint = url + "/newFireEvent"
                    # x = requests.post(endpoint, json=fire_event_json)

    except (KeyboardInterrupt, SystemExit):
        ser.close()
        exit()


if __name__ == "__main__":
    load_dotenv()
    topics = load_config(topics_path, "topics")
    client = init_mqtt_broker(client_name)

    main()
