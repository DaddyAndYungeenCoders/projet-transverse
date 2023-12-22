from app.core.config import load_config

topics = load_config("app/config/topics.yaml", "topics")


def on_message(client, userdata, message):
    print(f"Message received from {message.topic} : {message.payload.decode('utf-8')}")

    if message.topic == topics.get("simulator.auto-fire-event"):
        # post to webserver
        pass

    if message.topic == topics.get("simulator-view.sensor-changed"):
        # post to webserver
        pass


def is_topic_valid(topic_param: str) -> bool:
    if topic_param in topics:
        return True
    return False
