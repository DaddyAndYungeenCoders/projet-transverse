import os
import sys
from datetime import datetime

sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from main import db


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


def save_fire_event(fire_event_json):
    # save data in influx db
    id = fire_event_json["id"]
    intensity = fire_event_json["intensity"]
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
