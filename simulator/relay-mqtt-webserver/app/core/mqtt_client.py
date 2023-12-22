import paho.mqtt.client as mqtt
import os
import dotenv

from app.core.config import load_config

dotenv.load_dotenv()


class MqttClient:
    _instances = {}

    def __new__(cls, client_name, *args, **kwargs):
        if client_name not in cls._instances:
            cls._instances[client_name] = super(MqttClient, cls).__new__(cls)
            cls._instances[client_name]._init_mqtt_broker(client_name)
        return cls._instances[client_name]

    def _init_mqtt_broker(self, client_name):
        self.broker_ip = os.getenv("BROKER_IP")
        self.broker_port = int(os.getenv("BROKER_PORT"))
        self.user = os.getenv("BROKER_USER")
        self.pw = os.getenv("BROKER_PW")

        self.client = mqtt.Client(client_name)
        self.client.username_pw_set(username=self.user, password=self.pw)
        self.client.on_connect = self.on_connect
        self.client.on_publish = self.on_publish

        self.client.connect(self.broker_ip, self.broker_port)
        self.client.loop_start()

    def on_connect(self, client, userdata, flags, rc):
        print(f"Connected to {client} with result code {rc}")

    def on_publish(self, client, userdata, mid):
        print(f"Message published (mid={mid})")

    def publish_message(self, topic, message):
        # Publish the message
        result = self.client.publish(topic, message)

    def subscribe_to_topics(self):
        topics = load_config("../config/topics.yaml", "topics")
        self.client.subscribe(topics.get("simulator.auto_fire_event"))
        self.client.subscribe(topics.get("simulator_view.sensor_changed"))
