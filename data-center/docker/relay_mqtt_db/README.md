# Passerelle RF2

Afin que l'application trouve les modules python, il a été nécessaire d'ajouter cette ligne
`sys.path.append(os.path.dirname(os.path.abspath(__file__)))`

Cela provoque quelques erreurs de l'IDE mais cela fonctionne.

### Mise en place
```shell
# Depuis le repertoire passerelle-rf2
python3 -m venv .venv
source .venv/bin/activate
pip3 install -r requirements.txt
```

### Lancement de l'application
```shell
python3 app/main.py
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
docker run -p 8001:8001 <app-name>
```

### Grafana Queries
```Grafana
from(bucket: "project")
  |> range(start: v.timeRangeStart, stop:v.timeRangeStop)
  |> filter(fn: (r) =>
    r._measurement == "new_fire_event" and
    r._field == "intensity"
  )
```

```
from(bucket: "project")
|> range(start: v.timeRangeStart, stop:v.timeRangeStop)
|> filter(fn: (r) =>
r._measurement == "fire_event" and
r._field == "intensity"
)
```
