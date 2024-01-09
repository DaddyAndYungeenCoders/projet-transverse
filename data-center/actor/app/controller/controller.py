from app.core.config_vars import MQTT_CLIENT_NAME, ACTOR_VALIDATION_TOPIC
from app.core.mqtt_client import MqttClient

mqtt_client = MqttClient(MQTT_CLIENT_NAME)


def publish_validation_message(fire_event, is_fire_real):
    fire_event = {
        "id": fire_event["id"],
        "is_real": is_fire_real
    }
    mqtt_client.publish_message(ACTOR_VALIDATION_TOPIC, fire_event)
