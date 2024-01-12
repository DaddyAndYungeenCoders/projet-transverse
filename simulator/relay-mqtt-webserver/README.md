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
docker build -t <app-name>(:<tag>) .
docker login
# to tag it with your username
docker tag <app-name> <username>/<app-name>
docker push <username>/<app-name>(:<tag>)
```

### Run image
```shell
docker run -p 8001:8000 <app-name>
```