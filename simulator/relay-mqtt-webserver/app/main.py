from fastapi import FastAPI

from app.controller.controller import router
from app.core.config_utils import settings, logger

app = FastAPI(
    title=settings.app_name,
    debug=settings.debug,
)
app.include_router(router)


def on_startup():
    logger.info(f"Application <{settings.app_name}> started ! :)")
    logger.info(f"Running on http://{settings.host}:{settings.port}")


def on_shutdown():
    logger.info(f"Application <{settings.app_name}> is ending her life...")


app.add_event_handler("startup", on_startup)
app.add_event_handler("shutdown", on_shutdown)
