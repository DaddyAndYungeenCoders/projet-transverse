#!/bin/bash

OUTPUT_DIR="certs"

mkdir -p "${OUTPUT_DIR}"

# Définir les variables
SERVER_KEY="${OUTPUT_DIR}/brokerServer.key"
SERVER_CSR="${OUTPUT_DIR}/brokerServer.csr"
SERVER_CERT="${OUTPUT_DIR}/brokerServer.crt"


# Générer la clé privée
openssl genrsa -out "${SERVER_KEY}" 2048

# Générer la demande de certificat (CSR)
openssl req -new -out "${SERVER_CSR}" -key "${SERVER_KEY}" -subj "/C=FR/ST=Rhone/L=Lyon/O=DaddyYungees/OU=DY/CN=BrokerServer" -reqexts SAN -config <(echo "[req]"; echo "distinguished_name=req"; echo "[SAN]"; echo "subjectAltName=DNS:localhost,DNS:local.broker")


# Afficher les informations de la demande de certificat
openssl req -in "${SERVER_CSR}" -noout -text

# Signer la demande de certificat avec le certificat du Certificate Authority (CA)
openssl x509 -req -in "${SERVER_CSR}" -CA "${OUTPUT_DIR}/BrokerCA.crt" -CAkey "${OUTPUT_DIR}/BrokerCA.key" -CAcreateserial -out "${SERVER_CERT}" -days 3650

# Afficher les informations du certificat signé
openssl x509 -in "${SERVER_CERT}" -noout -text

echo "Étapes terminées avec succès:"
echo "  - Clé privée: ${SERVER_KEY}"
echo "  - Demande de certificat: ${SERVER_CSR}"
echo "  - Certificat signé: ${SERVER_CERT}"

