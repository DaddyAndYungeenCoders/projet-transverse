from pydantic import BaseModel


class SensorValue(BaseModel):
    id: int
    intensity: int
