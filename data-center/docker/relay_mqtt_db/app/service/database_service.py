import os
import sys
from datetime import datetime

sys.path.append(os.path.dirname(os.path.abspath(__file__)))
from core.config_utils import logger
from core.database_manager import DatabaseManager

# from main import db

try:
    db = DatabaseManager()
    logger.info(f"Successfully connected to database (Org: {db.org})")
except BaseException as e:
    logger.error(f"It seems there was an error initializing Database : {e}").org = os.getenv("INFLUXDB_V2_ORG")


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
    sensor_id = fire_event_json["id"]
    intensity = fire_event_json["intensity"]
    time = fire_event_json["startDate"]
    json_body = {
        "measurement": "fire_event",
        "tags": {
            "fire": "true",
            "sensor": f"sensor_{sensor_id}"
        },
        "time": f"{time}Z",
        "fields": {
            "id": sensor_id,
            "intensity": intensity
        }
    }
    db.insert_data(json_body)
