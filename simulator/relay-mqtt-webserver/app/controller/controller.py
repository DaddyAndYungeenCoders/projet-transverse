from fastapi import APIRouter, HTTPException
from app.models.MQTTMessage import MQTTMessage
from app.service.mqtt_service import init_mqtt_broker, publish_message

router = APIRouter()
mqtt_client = init_mqtt_broker("relay-mqtt-webserver")


@router.get("/test")
def hello_world():
    return {"message": "Hello World"}


@router.post("/publish/{topic}")
def publish_message_to_mqtt(topic: str, payload: MQTTMessage):
    try:
        # publish_message(mqtt_client, topic, message)
        message = payload
        print(f"publishing message to {topic} : {message}")
        return {"status": "Message published to MQTT"}
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"error publishing : {str(e)}")
