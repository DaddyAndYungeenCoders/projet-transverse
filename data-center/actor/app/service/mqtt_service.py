import time

from app.controller.controller import publish_validation_message
from app.core.config_vars import *
from app.service.fire_service import *

load_dotenv()


def is_topic_valid(topic_param: str) -> bool:
    return topic_param in topics


def on_message(client, userdata, message):
    logger.info(f"Message received from {message.topic} : {message.payload.decode('utf-8')}")
    data = message.payload.decode('utf-8')

    if message.topic == MANAGER_ASK_VALIDATION:
        # check if fire is real or not in simulator DB
        fire_reality = is_fire_real(data)
        # pub if fire is real or not
        if isinstance(fire_reality, bool):
            publish_validation_message(data, fire_reality)
        else:
            logger.error(f"Status of fire could not be determined : fire_reality : {fire_reality}")
            # maybe pub a message and let the manager handle it ?
    else:
        logger.error(f"Not a valid topic : {message.topic}")


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
