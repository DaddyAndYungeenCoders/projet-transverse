from pydantic import BaseModel


class MQTTMessage(BaseModel):
    content: str
