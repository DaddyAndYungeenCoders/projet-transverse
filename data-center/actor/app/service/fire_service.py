import json

import requests

from app.core.config_utils import logger
from app.core.config_vars import API_URL


def is_fire_real(fire_event):
    logger.info(fire_event)

    # event = json.loads(fire_event)

    logger.info(fire_event["id"])
    url = API_URL + "/fire-event" + "/is-real/" + str(fire_event["id"])
    res = requests.get(url)
    if res.status_code == 200:
        real_fire_event = res.json()
        logger.info(f"res : {real_fire_event}")
        return real_fire_event
    elif res.status_code == 404:
        logger.error(
            f"The request returned a {res.status_code} code status. Maybe the id {fire_event['id']} is invalid?")
    else:
        # if there is an error, we cannot know, so we assume the fire is real
        return True
