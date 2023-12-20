import datetime
import os
import requests
import paho.mqtt.client as mqtt
import json
import influxdb
from influxdb_client import InfluxDBClient, Point, WritePrecision
from influxdb_client.client.write_api import SYNCHRONOUS
from dotenv import load_dotenv
from database_manager import DatabaseManager


def init_mqtt_broker(client_name):
    broker_ip = os.getenv("BROKER_IP")
    broker_port = int(os.getenv("BROKER_PORT"))
    user = os.getenv("BROKER_USER")
    pw = os.getenv("BROKER_PW")

    client = mqtt.Client(client_name)
    client.username_pw_set(username=user, password=pw)
    client.connect(broker_ip, broker_port)

    print("subscribe to topics : project/fire-event and project/intervention")
    client.subscribe("project/fire-event")
    client.subscribe("project/intervention")

    client.on_message = on_message
    client.loop_start()
    return client


def init_influxdb_client():
    db = DatabaseManager()
    data = {
        "field": "testtest",
        "value": "123",
        "timestamp": datetime.datetime.now()
    }
    db.insert_data(data, {"label": "tagtest", "value": "tagvalue"})
    pass


def save_data_in_db(rec):
    # save data in influx db
    pass


def on_message(client, userdata, message):
    print("Received message (mqtt): {}".format(message.payload.decode("utf-8")))
    # print("message topic: {}".format(message.topic))
    # print("message qos: {}".format(message.qos))
    # print("message retain flag: {}".format(message.retain))
    if message.topic == "project/fire-event":
        try:
            rec = json.loads(message.payload.decode("utf-8"))
            print(rec["x"])
            print(rec["y"])
            print(rec["intensity"])
            print(rec["timestamp"])

            save_data_in_db(rec)

        except json.JSONDecodeError as e:
            print(f"Error decoding message: {e}")

    elif message.topic == "project/intervention":
        try:
            rec = json.loads(message.payload.decode("utf-8"))
            print(rec["id_team"])
            print(rec["id_fire-event"])
        except json.JSONDecodeError as e:
            print(f"Error decoding message: {e}")


def main():
    init_mqtt_broker("translator-service")
    init_influxdb_client()


if __name__ == '__main__':
    load_dotenv()
    main()
