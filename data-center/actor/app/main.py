from fastapi import FastAPI

from app.core.config_utils import settings, logger
from app.core.config_vars import MQTT_CLIENT_NAME
from app.core.mqtt_client import MqttClient

app = FastAPI(
    title=settings.app_name,
    debug=settings.debug,

)
mqtt_client = MqttClient(MQTT_CLIENT_NAME)


def on_startup():
    logger.info(f"Application {settings.app_name} started ! :)")


def on_shutdown():
    logger.info(f"Application {settings.app_name} is ending her life...")


app.add_event_handler("startup", on_startup)
app.add_event_handler("shutdown", on_shutdown)
