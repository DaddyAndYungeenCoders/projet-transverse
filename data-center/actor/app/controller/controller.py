from app.core.config import MQTT_CLIENT_NAME, ACTOR_VALIDATION_TOPIC
from app.core.mqtt_client import MqttClient

mqtt_client = MqttClient(MQTT_CLIENT_NAME)


def publish_validation_message(is_fire_real):
    mqtt_client.publish_message(ACTOR_VALIDATION_TOPIC, {"realFire": is_fire_real})
