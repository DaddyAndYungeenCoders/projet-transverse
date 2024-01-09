import requests

from app.core.config_vars import API_URL


def is_fire_real(fire_event):
    url = API_URL + "/api/fire-event" + f"/{fire_event['id']}"
    res = requests.get(url=url)
    if res.status_code == 200:
        real_fire_event = res.json()
        if real_fire_event["is_real"]:
            return True
        else:
            return False
    else:
        # if there is an error, we cannot know so we assume the fire is real
        return True
