from pydantic import BaseModel
from typing import Optional
from datetime import datetime


class Coords(BaseModel):
    latitude: float
    longitude: float


class Sensor(BaseModel):
    id: int
    intensity: int
    start_date: Optional[datetime]


class FireEvent(BaseModel):
    id: int
    coords: Coords
    intensity: int
    start_date: Optional[datetime]
    end_date: Optional[datetime]
    real: bool

    @classmethod
    def parse_obj(cls, obj):
        obj["start_date"] = datetime.fromtimestamp(obj["start_date"] / 1000.0) if "start_date" in obj else None
        obj["end_date"] = datetime.fromtimestamp(obj["end_date"] / 1000.0) if "end_date" in obj else None
        return super().model_validate(obj)
