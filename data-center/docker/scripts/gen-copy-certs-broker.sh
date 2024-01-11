#!/bin/bash

./gen-ca.sh
./gen-server.sh

docker compose up -d

docker cp certs/BrokerCA.crt mqtt:/etc/mosquitto/certs/
docker cp certs/brokerServer.crt mqtt:/etc/mosquitto/certs/
docker cp certs/brokerServer.key mqtt:/etc/mosquitto/certs/

docker compose up -d --force-recreate


