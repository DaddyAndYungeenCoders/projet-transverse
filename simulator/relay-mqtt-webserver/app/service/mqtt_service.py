import paho.mqtt.client as mqtt
import os
import dotenv

dotenv.load_dotenv()


def init_mqtt_broker(client_name):
    broker_ip = os.getenv("BROKER_IP")
    broker_port = int(os.getenv("BROKER_PORT"))
    user = os.getenv("BROKER_USER")
    pw = os.getenv("BROKER_PW")

    client = mqtt.Client(client_name)
    client.username_pw_set(username=user, password=pw)
    client.on_connect = on_connect
    client.on_publish = on_publish

    client.connect(broker_ip, broker_port)

    client.loop_start()
    return client


def on_connect(client, userdata, flags, rc):
    print(f"Connected to {client} with result code {rc}")


def on_publish(client, userdata, mid):
    print(f"Message published (mid={mid})")


def publish_message(client, topic, message):
    # Publish the message
    result = client.publish(topic, message)



