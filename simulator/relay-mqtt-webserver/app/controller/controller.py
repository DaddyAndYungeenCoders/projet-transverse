from fastapi import APIRouter, HTTPException
from app.models.FireEvent import FireEvent, SensorValue
from app.core.mqtt_client import MqttClient
from app.core.config import MQTT_CLIENT_NAME, topics, FIRE_EVENT_VIEW_TOPIC, SENSOR_CHANGE_VIEW_TOPIC
from app.service.mqtt_service import is_topic_valid

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

