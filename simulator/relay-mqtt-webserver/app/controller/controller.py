import requests
from fastapi import APIRouter, HTTPException

from app.core.config_utils import logger
from app.core.config_vars import MQTT_CLIENT_NAME, FIRE_EVENT_VIEW_TOPIC, SENSOR_CHANGE_VIEW_TOPIC
from app.core.mqtt_client import MqttClient
from app.models.FireEvent import FireEvent
from app.models.SensorValues import SensorValue

router = APIRouter()
mqtt_client = MqttClient(MQTT_CLIENT_NAME)


@router.post("/view/fireEvent")
async def user_fire_event(payload: FireEvent):
    # pub to simulator-view/sensor-changed
    try:
        if payload:
            mqtt_client.publish_message(FIRE_EVENT_VIEW_TOPIC, payload.model_dump_json())
            return {"status": f"Message published to {FIRE_EVENT_VIEW_TOPIC}"}
        else:
            raise HTTPException(status_code=400, detail=f"Request body must contain a FireEvent object")
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Error publishing to MQTT Broker : {str(e)}")


@router.post("/view/sensorChange")
async def sensor_change(payload: SensorValue):
    # pub to simulator-view/sensor-changed
    try:
        if payload:
            mqtt_client.publish_message(SENSOR_CHANGE_VIEW_TOPIC, payload.model_dump_json())
            return {"status": f"Message published to {SENSOR_CHANGE_VIEW_TOPIC}"}
        else:
            raise HTTPException(status_code=400, detail=f"Request body must contain a SensorChange object")
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Error publishing to MQTT Broker : {str(e)}")


def post_new_fire_event(url, data):
    try:
        res = requests.post(url, data)
        if res.status_code == 200:
            logger.info(f"The new fire_event {data} was properly sent to {url}")
        else:
            logger.error(f"The request returned a {res.status_code} code status : {res.json()}")
    except ConnectionError as e:
        logger.error(f"It appears that there was a problem connecting to the WebServer at {url}...")
        logger.error(e)
    except BaseException as e:
        logger.error(f"It appears that there was a problem connecting to the WebServer at {url}...")
        logger.error(e)


def put_new_sensor_values(url, data):
    try:
        res = requests.put(url, data)
        if res.status_code == 200:
            logger.info(f"The new sensor values {data} were properly updated. Status code : {res.status_code}")
        else:
            logger.error(f"The request returned a {res.status_code} code status : {res.json()}")
    except ConnectionError as e:
        logger.error(f"It appears that there was a problem connecting to the WebServer at {url}...")
        logger.error(e)
    except BaseException as e:
        logger.error(f"It appears that there was a problem connecting to the WebServer at {url}...")
        logger.error(e)
