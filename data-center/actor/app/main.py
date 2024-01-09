from fastapi import FastAPI

from app.core.config_utils import settings, logger

app = FastAPI(
    title=settings.app_name,
    debug=settings.debug,
)

logger.info(f"Application {settings.app_name} started ! :)")
