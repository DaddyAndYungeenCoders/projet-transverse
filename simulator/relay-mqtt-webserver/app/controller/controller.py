import json

from fastapi import APIRouter, HTTPException
from app.models.FireEvent import FireEvent
from app.core.mqtt_client import MqttClient
from app.core.config import mqtt_client_name
from app.service.mqtt_service import is_topic_valid

router = APIRouter()
mqtt_client = MqttClient(mqtt_client_name)


@router.get("/test")
def hello_world():
    return {"message": "Hello World"}


@router.post("/publish/{topic}")
def publish_message_to_mqtt(topic: str, payload: FireEvent):
    try:
        if is_topic_valid(topic):
            message_dict = payload.model_dump_json()
            print(f"publishing message to {topic} : {message_dict}")
            # mqtt_client.publish_message(topic, payload)
            return {"status": "Message published to MQTT"}
        else:
            raise HTTPException(status_code=400, detail=f"Topic is not valid")
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"error publishing : {str(e)}")
