from fastapi import APIRouter, HTTPException
from app.models.FireEvent import FireEvent
from app.core.mqtt_client import MqttClient
from app.core.config import MQTT_CLIENT_NAME, topics
from app.service.mqtt_service import is_topic_valid

router = APIRouter()
mqtt_client = MqttClient(MQTT_CLIENT_NAME)


@router.post("/publish/{topic}")
async def publish_message_to_mqtt(topic: str, payload: FireEvent):
    try:
        # for our use case, it should be simulator-view.fire-event and
        if is_topic_valid(topic):
            mqtt_client.publish_message(topics.get(topic), payload.model_dump_json())
            return {"status": "Message published to MQTT"}
        else:
            raise HTTPException(status_code=400, detail=f"Topic is not valid")
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"error publishing : {str(e)}")
