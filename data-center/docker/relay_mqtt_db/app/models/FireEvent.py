from pydantic import BaseModel
from typing import Optional
from datetime import datetime


class Coords(BaseModel):
    latitude: float
    longitude: float


class FireEvent(BaseModel):
    id: int
    # coords: Coords
    intensity: int
    startDate: Optional[datetime]
    # endDate: Optional[datetime]
    # real: bool

    @classmethod
    def parse_obj(cls, obj):
        obj["startDate"] = datetime.fromtimestamp(obj["startDate"] / 1000.0) if "startDate" in obj else None
        # obj["endDate"] = datetime.fromtimestamp(obj["endDate"] / 1000.0) if "endDate" in obj else None
        return super().model_validate(obj)
