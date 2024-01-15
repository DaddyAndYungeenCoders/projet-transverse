import json
from app.core.config_vars import MQTT_CLIENT_NAME, ACTOR_VALIDATION_TOPIC


def publish_validation_message(fire_event, is_fire_real):
    # avoid circular dependency
    from app.core.mqtt_client import MqttClient

    mqtt_client = MqttClient(MQTT_CLIENT_NAME)
    fire_event_json = json.loads(fire_event)

    json_event = json.loads(fire_event_json)
    json_fire_event = {
        "id": json_event["id"],
        "is_real": is_fire_real
    }
    fire_event_to_send = json.dumps(json_fire_event)
    mqtt_client.publish_message(ACTOR_VALIDATION_TOPIC, fire_event_to_send)
