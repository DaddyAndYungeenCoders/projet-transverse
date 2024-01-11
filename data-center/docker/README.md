# Docker

### Mise en place mosquitto
```shell
# /!\ Depuis le repertoire docker
mkdir -p mosquitto/config mosquitto/data mosquitto/log mosquitto/utils
echo "listener 1883 
protocol mqtt
persistence true
connection_messages true
persistence_location /mosquitto/data
allow_anonymous false
password_file mosquitto/config/mqtt_passwd" > mosquitto/config/mosquitto.conf
touch mosquitto/config/mqtt_passwd
docker compose up -d
docker exec -it mqtt sh
# /!\ Depuis le container mqtt
mosquitto_passwd -c /mosquitto/config/mqtt_passwd admin
exit 
# /!\ Depuis le repertoire docker
docker compose up -d --force-recreate
```
Cela permet de créer un broker mosquitto avec un utilisateur admin et un mot de passe stocké dans le fichier `mosquitto/config/mqtt_passwd`.


