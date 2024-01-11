from pydantic import BaseModel


class FireEvent(BaseModel):
    id: int
    intensity: float
