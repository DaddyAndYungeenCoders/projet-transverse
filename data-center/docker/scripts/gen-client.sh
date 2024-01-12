#!/bin/bash

# Définir le dossier de sortie
OUTPUT_DIR="certs"

if [ $# -eq 0 ]; then
    echo "Usage: $0 <client_name>"
    exit 1
fi

CLIENT_NAME="$1Client"

# Générer la clé privée du client
openssl genrsa -out "${OUTPUT_DIR}/${CLIENT_NAME}.key" 2048

# Générer la demande de certificat (CSR) pour le client avec CN personnalisé
openssl req -new -out "${OUTPUT_DIR}/${CLIENT_NAME}.csr" -key "${OUTPUT_DIR}/${CLIENT_NAME}.key" -subj "/C=FR/ST=Rhone/L=Lyon/O=DaddyYungees/OU=DY/CN=${CLIENT_NAME}"

# Signer la demande de certificat avec le certificat du Certificate Authority (CA)
openssl x509 -req -in "${OUTPUT_DIR}/${CLIENT_NAME}.csr" -CA "${OUTPUT_DIR}/BrokerCA.crt" -CAkey "${OUTPUT_DIR}/BrokerCA.key" -CAcreateserial -out "${OUTPUT_DIR}/${CLIENT_NAME}.crt" -days 3650

# Afficher les informations du certificat du client
openssl x509 -in "${OUTPUT_DIR}/${CLIENT_NAME}.crt" -noout -text

echo "Étapes terminées avec succès:"
echo "  - Clé privée du client: ${OUTPUT_DIR}/${CLIENT_NAME}.key"
echo "  - Demande de certificat du client: ${OUTPUT_DIR}/${CLIENT_NAME}.csr"
echo "  - Certificat du client signé: ${OUTPUT_DIR}/${CLIENT_NAME}.crt"
