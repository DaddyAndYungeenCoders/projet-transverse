import requests

from app.core.config_utils import logger


def post_new_fire_event(url, data):
    try:
        headers = {'Content-Type': 'application/json'}
        res = requests.post(url, json=data, headers=headers)

        if res.status_code == 200:
            logger.info(f"The new fire_event {data} was properly sent to {url}")
        else:
            logger.error(f"The request returned a {res.status_code} code status : {res.json()}")
    except ConnectionError as e:
        logger.error(f"It appears that there was a problem connecting to the WebServer at {url}...")
        logger.error(e)
    except BaseException as e:
        logger.error(f"Something went wrong : {e}")


def put_new_sensor_values(url, data):
    try:
        logger.info(f"Updating sensor values {data} to {url}")
        headers = {'Content-Type': 'application/json'}
        res = requests.put(url, json=data, headers=headers)
        if res.status_code == 200:
            logger.info(f"The new sensor values {data} were properly updated. Status code : {res.status_code}")
        else:
            logger.error(f"The request returned a {res.status_code} code status : {res.json()}")
    except ConnectionError as e:
        logger.error(f"It appears that there was a problem connecting to the WebServer at {url}...")
        logger.error(e)
    except BaseException as e:
        logger.error(f"Something went wrong : {e}")
