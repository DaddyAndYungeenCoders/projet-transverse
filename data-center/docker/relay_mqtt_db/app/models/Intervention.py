from pydantic import BaseModel


class Intervention(BaseModel):
    id: int
    intensity: float
    # to be completed
