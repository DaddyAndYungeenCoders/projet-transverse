from pydantic import BaseModel


# to be completed
class FireEvent(BaseModel):
    id: int
    int: int
    isReal: bool


class SensorValue(BaseModel):
    id: int
    intensity: int
