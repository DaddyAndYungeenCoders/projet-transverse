# This python program is designed to receive data from uBit through UART.
# It sends these data through MQTT to the Mosquitto Broker
# And it posts through HTTP to the WebServer in the Data Center

import time
import serial
import requests
from datetime import datetime
import paho.mqtt.client as mqtt
import json

# send serial message
SERIALPORT = "/dev/ttyACM0"
BAUDRATE = 115200
ser = serial.Serial()


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


def init_mqtt_broker():
    client = mqtt.Client("passerelle-rf2")

    broker_ip = "127.0.0.1"
    broker_port = 1883

    # client.on_connect = on_connect
    client.username_pw_set(username="admin", password="admin")
    client.connect(broker_ip, broker_port)

    print("subscribe to topic", "test/message")
    client.subscribe("test/message")

    client.on_message = on_message
    client.loop_start()
    return client


# def on_connect(client, userdata, flags, rc):
#     print("Connected flags ", str(flags), "result code ", str(rc))
#

def on_message(client, userdata, message):
    print("Received message (mqtt): {}".format(message.payload.decode("utf-8")))
    # print("message topic: {}".format(message.topic))
    # print("message qos: {}".format(message.qos))
    # print("message retain flag: {}".format(message.retain))
    try:
        rec = json.loads(message.payload.decode("utf-8"))
        print(rec["x"])
        print(rec["y"])
        print(rec["intensity"])
    except json.JSONDecodeError as e:
        print(f"Error decoding message: {e}")


def send_uart_message(msg):
    try:
        ser.write(msg.encode('utf-8'))
        print("Message <" + msg + "> sent to micro-controller.")
    except serial.SerialException as e:
        print("Error while sending message to micro-controller.")
        print(e)


def extract_data_from_serial(data):
    try:
        # Exemple de chaîne de données
        # T:23.45;L:500
        parts = data.split(';')

        temperature = float(parts[0].split(':')[1])
        lux = float(parts[1].split(':')[1])

        return temperature, lux
    # If there is an error while extracting data, we return -1, -1 to keep a trace
    except (ValueError, IndexError) as e:
        print(f"Erreur lors de l'extraction des données: {e}")
        return -1, -1


def main():
    # Init UART connection to microbit
    init_uart()
    client = init_mqtt_broker()
    # Init database connection
    # db = DatabaseManager()
    # db.connect()
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

                    # If message is not TL or LT, it's data from microbit, so we insert it in database
                    # if message_str not in MICRO_COMMANDS:
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
                    "intensity": "2"
                }
                print("publish to topic", "coucou")
                client.publish("test/message", "coucou")
                time.sleep(3)
                client.publish("test/message", json.dumps(to_send))
                a = 1

            # post data to webserver
            # url = "http://localhost:3000"
            # myobj = {'somekey': 'somevalue'}
            #
            # x = requests.post(url, json = myobj)

    except (KeyboardInterrupt, SystemExit):
        # db.print_last_ten_values()
        # db.close()
        # server.shutdown()
        # server.server_close()
        ser.close()
        exit()


def get_new_fire():
    # GET from the API with requests
    pass


def send_fire_event(fire_event):
    # Send the values trough UART
    pass


if __name__ == "__main__":
    main()
