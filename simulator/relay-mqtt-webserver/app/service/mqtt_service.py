from app.core.config import load_config

topics = load_config("app/config/topics.yaml", "topics")


def is_topic_valid(topic_param: str) -> bool:
    if topic_param in topics:
        return True
    return False
