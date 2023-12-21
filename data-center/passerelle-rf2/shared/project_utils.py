import yaml
import paho.mqtt.client as mqtt
import os
from dotenv import load_dotenv

load_dotenv()


# Load topics yaml file
def load_config(config_path, config_name):
    try:
        with open(config_path, 'r') as file:
            topics_yaml = yaml.safe_load(file)
            return topics_yaml.get(config_name, {})
    except yaml.YAMLError as e:
        print(f"Error loading YAML: {e}")


def init_mqtt_broker(client_name):
    broker_ip = os.getenv("BROKER_IP")
    broker_port = int(os.getenv("BROKER_PORT"))
    user = os.getenv("BROKER_USER")
    pw = os.getenv("BROKER_PW")

    client = mqtt.Client(client_name)
    client.username_pw_set(username=user, password=pw)
    client.connect(broker_ip, broker_port)

    client.loop_start()
    return client
