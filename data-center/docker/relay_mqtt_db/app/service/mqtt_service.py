import os
import sys
import time

sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from dotenv import load_dotenv
from core.config_utils import logger
from core.config_vars import *
from core.config_vars import MAX_SUB_RETRY, TOPICS_TO_SUBSCRIBE, FIRST_RECONNECT_DELAY, MAX_RECONNECT_COUNT, \
    RECONNECT_RATE, MAX_RECONNECT_DELAY

load_dotenv()


def on_message(client, userdata, message):
    print("Received message from {} : {}".format(message.topic, message.payload.decode("utf-8")))

    if message.topic == topics.get(topic_rf2_fire_event):
        try:
            fire_event_str = message.payload.decode("utf-8")
            fire_event = json.loads(fire_event_str)
            save_fire_event(fire_event)

        except json.JSONDecodeError as e:
            print(f"Error decoding message: {e}")

    elif message.topic == topics.get(topic_manager_intervention):
        try:
            intervention = json.loads(message.payload.decode("utf-8"))

            print(intervention["coords"])
            print(intervention["intensity"])
            print(intervention["startDate"])
            print(intervention["endDate"])
            print(intervention["validationStatus"])
            print(intervention["idInterventionTeam"])

            save_intervention(intervention)

        except json.JSONDecodeError as e:
            print(f"Error decoding message: {e}")

    else:
        logger.warn(f"Topic {topics.get()} is not valid.")


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
                logger.warn("It seems that there was an error (error code: %s), reattempting... %d tries left",
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
    logger.error("Reconnect failed after %s attempts. Exiting...", reconnect_count)


def on_publish(client, userdata, mid):
    logger.info(f"Message published (mid={mid})")
