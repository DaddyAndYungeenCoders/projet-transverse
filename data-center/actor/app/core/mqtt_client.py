import paho.mqtt.client as mqtt

from app.service.mqtt_service import *


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
        except ConnectionError as e:
            logger.error("It appears that there was an error while connecting to the Broker : %s", str(e))
        self.client.loop_start()

    def publish_message(self, topic, message):
        # Publish the message
        result = self.client.publish(topic, message)
        logger.info(f"publishing message to {topic} : {message} (response code: {result})")
