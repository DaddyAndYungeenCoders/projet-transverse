import json
import os
import sys

import requests

sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from core.config_utils import logger
from core.config_vars import MQTT_CLIENT_NAME, RF2_FIRE_EVENT_TOPIC, API_URL


def publish_fire_event(fire_event):
    # avoid circular dependency
    from core.mqtt_client import MqttClient

    mqtt_client = MqttClient(MQTT_CLIENT_NAME)
    logger.info(f"Publishing {json.dumps(fire_event)} to {RF2_FIRE_EVENT_TOPIC}")
    mqtt_client.publish_message(RF2_FIRE_EVENT_TOPIC, fire_event)


def post_fire_event(fire_event):
    url = API_URL + "/fire-event/create/dto"
    event_to_send = {
        "id": fire_event["id"],
        "intensity": fire_event["intensity"]
    }
    try:
        headers = {"Content-Type": "application/json"}
        res = requests.get(url, json=event_to_send, headers=headers)

        if res.status_code == 200:
            logger.info(f"The new fire_event {fire_event} was properly sent to {url}")
        else:
            logger.error(f"The request returned a {res.status_code} code status : {res.json()}")
    except ConnectionError as e:
        logger.error(f"It appears that there was a problem connecting to the WebServer at {url}...")
        logger.error(e)
    except BaseException as e:
        logger.error(f"It appears that there was a problem connecting to the WebServer at {url}...")
        logger.error(e)
