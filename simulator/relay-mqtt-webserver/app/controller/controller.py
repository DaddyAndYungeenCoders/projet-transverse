from fastapi import APIRouter, HTTPException

from app.core.config_utils import logger
from app.core.config_vars import MQTT_CLIENT_NAME, FIRE_EVENT_VIEW_TOPIC, SENSOR_CHANGE_VIEW_TOPIC
from app.core.mqtt_client import MqttClient
from app.models.FireEvent import FireEvent
from app.models.SensorValues import SensorValue

router = APIRouter()
mqtt_client = MqttClient(MQTT_CLIENT_NAME)


@router.post("/api/view/fire-event/")
async def user_fire_event(payload: FireEvent):
    # pub to simulator-view/sensor-changed
    logger.info(f"Received payload: {payload}")
    try:
        if payload:
            mqtt_client.publish_message(FIRE_EVENT_VIEW_TOPIC, payload.model_dump_json())
            return {"status": f"Message published to {FIRE_EVENT_VIEW_TOPIC}"}
        else:
            raise HTTPException(status_code=400, detail=f"Request body must contain a FireEvent object")
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Error publishing to MQTT Broker : {str(e)}")


@router.post("/api/view/sensor-changed")
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
