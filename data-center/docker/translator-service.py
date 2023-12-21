from datetime import datetime
import sys
import os
import requests
import paho.mqtt.client as mqtt
import json
import yaml
from dotenv import load_dotenv
from shared.database_manager import DatabaseManager
from shared.project_utils import load_config, init_mqtt_broker

topics_path = "shared/config/topics.yaml"
client_name = "translator-service"
topic_rf2_fire_event = "rf2.fire_event"
topic_manager_intervention = "manager.intervention"


def init_influxdb_client():
    db = DatabaseManager()
    data = {
        "field": "testtest",
        "value": "123",
        "timestamp": datetime.now()
    }
    # db.insert_data(data, {"label": "tagtest", "value": "tagvalue"})
    return db


def save_data_in_db(rec):
    # save data in influx db
    db.insert_data(rec)

    data_sensor = db.get_data("sensor_new_value")
    data_fire = db.get_data("fire_event")
    print(data_sensor)
    print(data_fire)
    results = []
    resultss = []
    for item in data_sensor:
        for record in item.records:
            results.append((record.get_field(), record.get_value()))
    for item in data_fire:
        for record in item.records:
            resultss.append((record.get_field(), record.get_value()))
    print(results)
    print(resultss)


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
        db = DatabaseManager()
        init_influxdb_client()

        print('Press Ctrl-C to quit.')

        print("subscribe to topics : " + topics.get(topic_rf2_fire_event) + " and "
              + topics.get(topic_manager_intervention))
        client.subscribe(topics.get(topic_rf2_fire_event))
        client.subscribe(topics.get(topic_manager_intervention))
        client.on_message = on_message

        json_body = [
            {
                "measurement": "fire_event",
                "tags": {
                    "fire": "true",
                    "sensor": "sensor1"
                },
                "time": datetime.utcnow().isoformat() + "Z",  # Adjust this based on your JSON structure
                "fields": {
                    "x": 1,
                    "y": 2,
                    "intensity": 1
                    # Add more fields as needed
                }
            }
        ]
        save_data_in_db(json_body)

        # while True:
        #     main()

    except KeyboardInterrupt:
        exit()
