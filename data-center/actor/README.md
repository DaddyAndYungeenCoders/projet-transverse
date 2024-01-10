# Actor Service

### Mise en place
```shell
python3 -m venv .venv
source .venv/bin/activate
pip3 install -r requirements.txt
```

### Lancement de l'application
```shell
uvicorn app.main:app --reload --host 0.0.0.0 --port 7000
```