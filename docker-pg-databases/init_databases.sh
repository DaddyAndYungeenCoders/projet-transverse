#!/bin/sh

# Perform all actions as $POSTGRES_USER
export PGPASSWORD=$POSTGRES_PASSWORD

echo "Creating databases..."

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE DATABASE emergency;
    CREATE DATABASE simulateur;
EOSQL

echo "Databases created."

echo "Initializing db1..."
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "emergency" -f /sql_scripts/emergency.sql

echo "Initializing db2..."
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "simulateur" -f /sql_scripts/simulateur.sql
echo "db2 DATA"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "simulateur" -f /sql_scripts/simulateur_DATA.sql

echo "All databases have been initialized."
