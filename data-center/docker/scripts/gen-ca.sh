#!/bin/bash
OUTPUT_DIR="certs"

mkdir -p "${OUTPUT_DIR}"

# Définir les variables
CA_KEY="${OUTPUT_DIR}/BrokerCA.key"
CA_CRT="${OUTPUT_DIR}/BrokerCA.crt"

# Générer la clé privée du Certificate Authority (CA)
openssl genrsa -out "${CA_KEY}" 2048

# Générer le certificat auto-signé pour le Certificate Authority (CA)
openssl req -new -x509 -days 3650 -key "${CA_KEY}" -out "${CA_CRT}" -subj "/C=FR/ST=Rhone/L=Lyon/O=DaddyYungees/OU=DY/CN=BrokerCA"

# Afficher les informations du certificat auto-signé
openssl x509 -in "${CA_CRT}" -noout -text

echo "Étapes terminées avec succès:"
echo "  - Clé privée du Certificate Authority (CA): ${CA_KEY}"
echo "  - Certificat auto-signé du Certificate Authority (CA): ${CA_CRT}"

