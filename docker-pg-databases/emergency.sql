-- Création des types ENUM pour les champs enum
CREATE TYPE validation_status AS ENUM ('0', '1', '2');
CREATE TYPE product_type AS ENUM ('type1', 'type2', 'type3'); -- Remplacer 'type1', 'type2', 'type3' par les valeurs réelles

-- Table Sensor
CREATE TABLE Sensor (
    id SERIAL PRIMARY KEY,
    coords INT[2]
);

-- Table FireEvent
CREATE TABLE FireEvent (
    id SERIAL PRIMARY KEY,
    coords INT[2],
    intensity INT,
    start_date DATE,
    end_date DATE,
    validation_status validation_status,
    id_equipe_intervention INT
);

-- Table historique
CREATE TABLE historique (
    sensor_id INT,
    fire_event_id INT,
    date_time DATE,
    intensite INT,
    FOREIGN KEY (sensor_id) REFERENCES Sensor(id),
    FOREIGN KEY (fire_event_id) REFERENCES FireEvent(id)
);

-- Table User
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50),
    is_admin BOOLEAN,
    created_date DATE
);

-- Table Fireman
CREATE TABLE Fireman (
    id SERIAL PRIMARY KEY,
    lastname VARCHAR(50),
    firstname VARCHAR(50),
    birthdate DATE,
    fire_mastery INT,
    id_team INT
    -- Assumption: id_team is a foreign key referencing Team table
);

-- Table Team
CREATE TABLE Team (
    id SERIAL PRIMARY KEY,
    chief_officer VARCHAR(50),
    stamina INT,
    id_firestation INT
    -- Assumption: id_firestation is a foreign key referencing FireStation table
);

-- Table Vehicule
CREATE TABLE Vehicule (
    id SERIAL PRIMARY KEY,
    capacity INT,
    weight INT,
    speed INT,
    product_type product_type,
    fire_mastery INT,
    id_fire_station INT
    -- Assumption: id_fire_station is a foreign key referencing FireStation table
);

-- Table FireStation
CREATE TABLE FireStation (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    address VARCHAR(50),
    coords INT[2]
);

-- Table Team_Intervention_Reference
CREATE TABLE Team_Intervention_Reference (
    id_fire_event INT,
    id_team INT,
    duration INT,
    PRIMARY KEY (id_fire_event, id_team),
    FOREIGN KEY (id_fire_event) REFERENCES FireEvent(id),
    FOREIGN KEY (id_team) REFERENCES Team(id)
);

-- Ajout des contraintes de clé étrangère
ALTER TABLE FireEvent ADD CONSTRAINT fk_equipe_intervention FOREIGN KEY (id_equipe_intervention) REFERENCES Team(id);
ALTER TABLE Fireman ADD CONSTRAINT fk_team FOREIGN KEY (id_team) REFERENCES Team(id);
ALTER TABLE Team ADD CONSTRAINT fk_firestation FOREIGN KEY (id_firestation) REFERENCES FireStation(id);
ALTER TABLE Vehicule ADD CONSTRAINT fk_fire_station FOREIGN KEY (id_fire_station) REFERENCES FireStation(id);

-- Assurez-vous de remplacer 'type1', 'type2', 'type3' par les valeurs réelles de votre énumération pour product_type.

