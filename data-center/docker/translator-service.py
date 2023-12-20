import datetime
import sys
import os
import requests
import paho.mqtt.client as mqtt
import json
import yaml
from dotenv import load_dotenv
from database_manager import DatabaseManager

sys.path.insert(0, '../../utils')
from utils import load_config, init_mqtt_broker

topics_path = "../../utils/config/topics.yaml"
client_name = "translator-service"
topic_rf2_fire_event = "rf2.fire_event"
topic_manager_intervention = "manager.intervention"


def init_influxdb_client():
    db = DatabaseManager()
    data = {
        "field": "testtest",
        "value": "123",
        "timestamp": datetime.datetime.now()
    }
    # db.insert_data(data, {"label": "tagtest", "value": "tagvalue"})
    pass


def save_data_in_db(rec):
    # save data in influx db
    pass


def on_message(client, userdata, message):
    print("Received message from {} : {}".format(message.topic, message.payload.decode("utf-8")))
    # print("message topic: {}".format(message.topic))
    # print("message qos: {}".format(message.qos))
    # print("message retain flag: {}".format(message.retain))
    if message.topic == topics.get(topic_rf2_fire_event):
        try:
            fire_event = json.loads(message.payload.decode("utf-8"))

            print(fire_event["coords"])
            print(fire_event["intensity"])
            print(fire_event["startDate"])
            print(fire_event["endDate"])
            print(fire_event["validationStatus"])
            print(fire_event["idInterventionTeam"])

            save_data_in_db(fire_event)

        except json.JSONDecodeError as e:
            print(f"Error decoding message: {e}")

    elif message.topic == topics.get(topic_manager_intervention):
        try:
            intervention = json.loads(message.payload.decode("utf-8"))
            print(intervention["sensorId"])
            print(intervention["fireEventId"])
            print(intervention["datetime"])
            print(intervention["intensite"])

            save_data_in_db(intervention)

        except json.JSONDecodeError as e:
            print(f"Error decoding message: {e}")


def main():
    pass


if __name__ == '__main__':
    try:
        load_dotenv()
        topics = load_config(topics_path, "topics")
        client = init_mqtt_broker(client_name)

        print("subscribe to topics : " + topics.get(topic_rf2_fire_event) + " and "
                                        + topics.get(topic_manager_intervention))
        client.subscribe(topics.get(topic_rf2_fire_event))
        client.subscribe(topics.get(topic_manager_intervention))
        client.on_message = on_message

        db = DatabaseManager()
        init_influxdb_client()
        print('Press Ctrl-C to quit.')
        while True:
            main()

    except KeyboardInterrupt:
        exit()
