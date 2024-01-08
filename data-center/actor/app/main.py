from fastapi import FastAPI
from app.controller.controller import router
from app.core.config import settings

app = FastAPI(
    title=settings.app_name,
    debug=settings.debug,
)
app.include_router(router)
