from fastapi import FastAPI

from app.controller.controller import router
from app.core.config_utils import settings, logger

app = FastAPI(
    title=settings.app_name,
    debug=settings.debug,
)
app.include_router(router)

logger.info(f"Application {settings.app_name} has started ! :)")
