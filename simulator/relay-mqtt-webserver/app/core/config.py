import yaml
from dotenv import load_dotenv
from pydantic_settings import BaseSettings

load_dotenv()
mqtt_client_name = "relay-mqtt-webserver"


# Load topics yaml file
def load_config(config_path, config_name):
    try:
        with open(config_path, 'r') as file:
            topics_yaml = yaml.safe_load(file)
            return topics_yaml.get(config_name, {})
    except yaml.YAMLError as e:
        print(f"Error loading YAML: {e}")


class Settings(BaseSettings):
    app_name: str = "Relay service MQTT - WebServer API"
    debug: bool = False


settings = Settings()
