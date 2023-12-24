import requests
import os
import time
from app.core.config import *


def is_topic_valid(topic_param: str) -> bool:
    if topic_param in topics:
        return True
    return False


def on_message(client, userdata, message):
    print(f"Message received from {message.topic} : {message.payload.decode('utf-8')}")
    data = message.payload.decode('utf-8')

    if message.topic == topics.get("simulator.auto-fire-event"):
        print("Received new fireEvent from Simulator")
        # post new fire_event to webserver, that was auto generated by simulator
        res = requests.post(API_URL + "/newAutoFireEvent", data)
        print(res.json())

    if message.topic == topics.get("simulator-view.sensor-changed"):
        print("Received new sensor value from user")
        # post new values of sensor to webserver, that was given by user from view
        pass

    if message.topic == topics.get("simulator.new-sensor-value"):
        print("Received new sensor value from Simulator")
        # post new values of sensor to webserver, that was given by simulation from simulator
        pass


def on_connect(client, userdata, flags, rc):
    if rc == 0:
        logger.info("Successfully connected to Broker %s:%s", os.getenv('BROKER_IP'), os.getenv('BROKER_PORT'))
        res = ""
        current_tries = 0
        while res != 0 and current_tries < MAX_SUB_RETRY:
            current_tries += 1
            res = client.subscribe(TOPICS_TO_SUBSCRIBE)
            if res[0] == 0:
                logger.info("Successfully subscribed to topics")
                break
            else:
                logger.info("It seems that there was an error (error code: %s), reattempting... %d tries left",
                            str(res),
                            MAX_SUB_RETRY - current_tries)
            time.sleep(1)
    else:
        logger.error("Error while attempting to connect to Broker, return code : %d", rc)


def on_disconnect(client, userdata, rc):
    logger.info("Disconnected with result code: %s", rc)
    reconnect_count, reconnect_delay = 0, FIRST_RECONNECT_DELAY
    while reconnect_count < MAX_RECONNECT_COUNT:
        logger.info("Reconnecting in %d seconds...", reconnect_delay)
        time.sleep(reconnect_delay)

        try:
            client.reconnect()
            logger.info("Reconnected successfully!")
            return
        except Exception as err:
            logger.error("%s. Reconnect failed. Retrying...", err)

        reconnect_delay *= RECONNECT_RATE
        reconnect_delay = min(reconnect_delay, MAX_RECONNECT_DELAY)
        reconnect_count += 1
    logger.info("Reconnect failed after %s attempts. Exiting...", reconnect_count)


def on_publish(client, userdata, mid):
    logger.info(f"Message published (mid={mid})")
