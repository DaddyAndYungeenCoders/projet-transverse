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
