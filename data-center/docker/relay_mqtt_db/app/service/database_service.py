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
    time = datetime.utcnow()
    fire_event_id = fire_event_json["id"]
    coords = fire_event_json["coords"]
    intensity = fire_event_json["intensity"]
    start_date = fire_event_json["start_date"]
    end_date = fire_event_json["end_date"]
    real = fire_event_json["real"]
    time = str(time)
    json_body = {
        "measurement": "fire_event",
        "tags": {
            "fire": "true",
            "sensor": f"fire_event_{fire_event_id}"
        },
        "time": f"{time}Z",
        "fields": {
            "id": fire_event_id,
            "latitude": coords["latitude"],
            "longitude": coords["longitude"],
            "intensity": intensity,
            "startDate": start_date,
            "endDate": end_date,
            "real": real,
        }
    }
    db.insert_data(json_body)


def save_new_sensor_values(sensor_values_json):
    # save data in influx db
    sensor_id = sensor_values_json["id"]
    intensity = sensor_values_json["intensity"]
    time = sensor_values_json["startDate"]
    json_body = {
        "measurement": "sensor",
        "tags": {
            "sensor": f"sensor_{sensor_id}"
        },
        "time": f"{time}Z",
        "fields": {
            "id": sensor_id,
            "intensity": intensity
        }
    }
    db.insert_data(json_body)
