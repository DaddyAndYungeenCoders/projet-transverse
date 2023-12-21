# This python program is designed to receive data from uBit through UART.
# It sends these data through MQTT to the Mosquitto Broker
# And it posts through HTTP to the WebServer in the Data Center

import time
import serial
import os
import sys
import requests
from dotenv import load_dotenv
from datetime import datetime
import paho.mqtt.client as mqtt
import json

from shared.database_manager import DatabaseManager
from shared.project_utils import load_config, init_mqtt_broker

# import shared.database_manager as database_manager
# import shared.project_utils as utils

# send serial message
SERIALPORT = "/dev/ttyACM0"
BAUDRATE = 115200
ser = serial.Serial()

client_name = "passerelle-rf2"
topics_path = "shared/config/topics.yaml"
topic_rf2_fire_event = "rf2.fire_event"


def init_uart():
    # ser = serial.Serial(SERIALPORT, BAUDRATE)
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


# def send_uart_message(msg):
#     try:
#         ser.write(msg.encode('utf-8'))
#         print("Message <" + msg + "> sent to micro-controller.")
#     except serial.SerialException as e:
#         print("Error while sending message to micro-controller.")
#         print(e)


def extract_data_from_serial(data):
    try:
        # Exemple de chaîne de données
        # x:2;y:1;int:6
        parts = data.split(';')

        x = float(parts[0].split(':')[1])
        y = float(parts[1].split(':')[1])
        intensity = float(parts[2].split(':')[1])

        return x, y, intensity
    # If there is an error while extracting data, we return -1, -1, -1 to keep a trace
    except (ValueError, IndexError) as e:
        print(f"Erreur lors de l'extraction des données: {e}")
        return -1, -1, -1


def main():
    # Init UART connection to microbit
    # init_uart()

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
                    message_str = message.decode('utf-8')

                    print("(SERIAL) received: " + message_str)

                    #     temp, lux = extractDataFromSerial(message_str)
                    #     # Insert data in database
                    #     if db.insert_data(temp, lux, datetime.now()):
                    #         print("Data inserted in database :", temp, lux, datetime.now())
                    #     else:
                    #         print("Error while inserting data in database")

            # send data to MQTT topic
            while a == 0:
                to_send = {
                    "x": "10",
                    "y": "5",
                    "intensity": "2",
                    "timestamp": datetime.now().isoformat()
                }
                client.publish(topics.get(topic_rf2_fire_event), "coucou")
                time.sleep(3)
                client.publish(topic_rf2_fire_event, json.dumps(to_send))
                a = 1

            # post data to webserver
            # url = "http://localhost:3000"
            # myobj = {'somekey': 'somevalue'}
            #
            # x = requests.post(url, json = myobj)

    except (KeyboardInterrupt, SystemExit):
        # db.close()
        # ser.close()
        exit()


if __name__ == "__main__":
    load_dotenv()
    topics = load_config(topics_path, "topics")
    client = init_mqtt_broker(client_name)

    # main()
