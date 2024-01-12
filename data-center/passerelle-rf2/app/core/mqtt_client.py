import os
import sys

import paho.mqtt.client as mqtt

sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from service.mqtt_service import *


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
        # self.client.tls_set(
        #     ca_certs='./server-ca.crt',
        #     certfile='./client.crt',
        #     keyfile='./client.key'
        # )
        self.client.username_pw_set(username=self.user, password=self.pw)
        self.client.on_connect = on_connect
        self.client.on_disconnect = on_disconnect
        self.client.on_publish = on_publish
        self.client.on_message = on_message

        try:
            self.client.connect(self.broker_ip, self.broker_port)
            logger.info(f"Successfully connected to Broker at {self.broker_ip}:{self.broker_port} !")
        except BaseException as e:
            logger.error("It appears that there was an error while connecting to the Broker : %s. Maybe it's down ?",
                         str(e))
        self.client.loop_start()

    def publish_message(self, topic, message):
        # Publish the message
        result = self.client.publish(topic, message)
        if result[0] == 0:
            logger.info(f"Response code : {result}")
            logger.info(f"Successfully published {message} to topic {topic} !")
        else:
            logger.info(f"There was an error publishing message ... res = {result[0]}")