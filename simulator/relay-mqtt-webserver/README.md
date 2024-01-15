# Actor Service

### Mise en place
```shell
python3 -m venv .venv
source .venv/bin/activate
pip3 install -r requirements.txt
```

### Lancement de l'application
```shell
uvicorn app.main:app --reload --host 0.0.0.0 --port 8000
```

### Build et push de l'image pour Docker
```shell
docker build -t relay .
docker login
# to tag it with your username
docker tag <app-name> <username>/<app-name>
docker push <username>/<app-name>(:<tag>)
```

### Run image
```shell
docker run -p 8100:8000 --network=docker_firenet relay
```