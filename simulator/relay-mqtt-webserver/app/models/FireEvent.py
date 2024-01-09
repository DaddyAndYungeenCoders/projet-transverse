from pydantic import BaseModel


# to be completed
class FireEvent(BaseModel):
    id: int
    # coords: [float, float]
    # realIntensity: int
    # startDate: datetime
    # endDate: datetime
    isReal: bool


class SensorValue(BaseModel):
    id: int
    intensity: int
