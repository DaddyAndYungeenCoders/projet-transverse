from fastapi import FastAPI
from app.core.config import *
from app.controller.controller import router

app = FastAPI(
    title=settings.app_name,
    debug=settings.debug

)
app.include_router(router)

if __name__ == '__main__':
    pass

# init_mqtt_broker("relay-mqtt-webserver")
