from datetime import datetime
import json
from dotenv import load_dotenv
from shared.database_manager import DatabaseManager
from shared.project_utils import load_config, init_mqtt_broker

topics_path = "shared/config/topics.yaml"
client_name = "translator-service"
topic_rf2_fire_event = "rf2.fire_event"
topic_manager_intervention = "manager.intervention"


def init_influxdb_client():
    influx_db = DatabaseManager()
    return influx_db


def save_intervention(intervention):
    # save data in influx db
    id = intervention["id"]
    intensity = intervention["intensity"]
    # other fields from intervention to get
    # json body to adapt according to fields
    json_body = {
        "measurement": "intervention",
        "tags": {
            "fire": "true",
            "sensor": f"sensor{id}"
        },
        "time": datetime.utcnow().isoformat() + "Z",
        "fields": {
            "id": id,
            "intensity": intensity
        }
    }
    db.insert_data(json_body)


def save_fire_event(fire_event):
    # save data in influx db
    id = fire_event["id"]
    intensity = fire_event["intensity"]
    json_body = {
        "measurement": "fire_event",
        "tags": {
            "fire": "true",
            "sensor": f"sensor{id}"
        },
        "time": datetime.utcnow().isoformat() + "Z",
        "fields": {
            "id": id,
            "intensity": intensity
        }
    }
    db.insert_data(json_body)

    # data_fire = db.get_data("fire_event")


def on_message(client, userdata, message):
    print("Received message from {} : {}".format(message.topic, message.payload.decode("utf-8")))

    if message.topic == topics.get(topic_rf2_fire_event):
        try:
            fire_event_str = message.payload.decode("utf-8")

            fire_event = json.loads(fire_event_str)
            save_fire_event(fire_event)

        except json.JSONDecodeError as e:
            print(f"Error decoding message: {e}")

    elif message.topic == topics.get(topic_manager_intervention):
        try:
            intervention = json.loads(message.payload.decode("utf-8"))

            print(intervention["coords"])
            print(intervention["intensity"])
            print(intervention["startDate"])
            print(intervention["endDate"])
            print(intervention["validationStatus"])
            print(intervention["idInterventionTeam"])

            save_intervention(intervention)

        except json.JSONDecodeError as e:
            print(f"Error decoding message: {e}")


def main():
    pass


if __name__ == '__main__':
    try:
        load_dotenv()
        topics = load_config(topics_path, "topics")
        client = init_mqtt_broker(client_name)
        db = init_influxdb_client()

        print('Press Ctrl-C to quit.')

        print("subscribe to topics : " + topics.get(topic_rf2_fire_event) + " and "
              + topics.get(topic_manager_intervention))

        client.subscribe(topics.get(topic_rf2_fire_event))
        client.subscribe(topics.get(topic_manager_intervention))
        client.on_message = on_message

        while True:
            main()

    except KeyboardInterrupt:
        exit()
