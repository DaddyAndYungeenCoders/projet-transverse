from fastapi import FastAPI
from app.core.config import *
from app.controller.controller import router
from app.core.mqtt_client import *
from app.service.mqtt_service import on_message

app = FastAPI(
    title=settings.app_name,
    debug=settings.debug

)
app.include_router(router)

if __name__ == '__main__':
    client = MqttClient(mqtt_client_name)
    client.subscribe_to_topics()
    client.on_message = on_message
