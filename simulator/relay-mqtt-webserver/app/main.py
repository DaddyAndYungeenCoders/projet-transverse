from fastapi import FastAPI
from app.core.config import *
from app.controller.controller import router
from app.core.mqtt_client import *
# from app.service.mqtt_service import on_message
import app.service.mqtt_service as mqtt

app = FastAPI(
    title=settings.app_name,
    debug=settings.debug

)
app.include_router(router)
