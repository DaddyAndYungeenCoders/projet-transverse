import os
import sys

sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from core.database_manager import DatabaseManager
from core.config_utils import logger


def init_influxdb_client():
    try:
        influx_db = DatabaseManager()
        return influx_db
    except BaseException as e:
        logger.error(f"It seems there was an error initializing Database : {e}")


def save_intervention(db, intervention):
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


def save_fire_event(db, fire_event):
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
