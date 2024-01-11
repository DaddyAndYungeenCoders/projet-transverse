import os
import time

from app.core.config_utils import *
from app.core.config_vars import topics, MAX_SUB_RETRY, TOPICS_TO_SUBSCRIBE, FIRST_RECONNECT_DELAY, MAX_RECONNECT_COUNT, \
    RECONNECT_RATE, MAX_RECONNECT_DELAY, API_URL, NEW_AUTO_FIRE, NEW_SENSOR_VALUES, NEW_SENSOR_VALUES_TOPIC, \
    AUTO_EVENT_SIM_TOPIC


def is_topic_valid(topic_param: str) -> bool:
    return topic_param in topics


def on_message(client, userdata, message):
    # avoid circular dependency
    from app.service.http_service import post_new_fire_event, put_new_sensor_values

    logger.info(f"Message received from {message.topic} : {message.payload.decode('utf-8')}")
    data = message.payload.decode('utf-8')

    if message.topic == AUTO_EVENT_SIM_TOPIC:
        logger.info("Received new fireEvent from Simulator")
        # post new fire_event to webserver, that was auto generated by simulator
        post_new_fire_event(API_URL + NEW_AUTO_FIRE, data)

    if message.topic == NEW_SENSOR_VALUES_TOPIC:
        logger.info("Received new sensor value from Simulator")
        # post new values of sensor to webserver, that was given by simulation from simulator
        update_sensor_endpoint = format(NEW_SENSOR_VALUES, data["id"])
        put_new_sensor_values(API_URL + update_sensor_endpoint, data)


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
                logger.error("It seems that there was an error (error code: %s), reattempting... %d tries left",
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
            logger.warn("%s. Reconnect failed. Retrying...", err)

        reconnect_delay *= RECONNECT_RATE
        reconnect_delay = min(reconnect_delay, MAX_RECONNECT_DELAY)
        reconnect_count += 1
    logger.error("Reconnect failed after %s attempts. Exiting...", reconnect_count)


def on_publish(client, userdata, mid):
    logger.info(f"Message published (mid={mid})")
