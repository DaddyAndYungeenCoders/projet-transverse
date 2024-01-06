-- Création des tables

CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50),
    created_date DATE
);

CREATE TABLE Sensor (
    id SERIAL PRIMARY KEY,
    latitude FLOAT,
    longitude FLOAT
);

CREATE TABLE FireEvent (
    id SERIAL PRIMARY KEY,
    latitude FLOAT,
    longitude FLOAT,
    real_intensity INT,
    start_date DATE,
    end_date DATE,
    is_real BOOLEAN
);

CREATE TABLE detects (
    id_sensor INT,
    id_fire_event INT,
    intensity FLOAT,
    PRIMARY KEY (id_sensor, id_fire_event),
    FOREIGN KEY (id_sensor) REFERENCES Sensor(id),
    FOREIGN KEY (id_fire_event) REFERENCES FireEvent(id)
);

CREATE TABLE Historique (
    id_user INT,
    id_fire_event INT,
    date_creation DATE,
    PRIMARY KEY (id_user, id_fire_event),
    FOREIGN KEY (id_user) REFERENCES Users(id),
    FOREIGN KEY (id_fire_event) REFERENCES FireEvent(id)
);

CREATE TABLE FireStation (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    address VARCHAR(50),
    latitude FLOAT,
    longitude FLOAT
);

CREATE TABLE Team (
    id SERIAL PRIMARY KEY,
    fire_station_id INT,
    stamina INT,
    FOREIGN KEY (fire_station_id) REFERENCES FireStation(id)
);

CREATE TABLE intervention (
    id_fire_event INT,
    id_team INT,
    duration INT,
    PRIMARY KEY (id_fire_event, id_team),
    FOREIGN KEY (id_fire_event) REFERENCES FireEvent(id),
    FOREIGN KEY (id_team) REFERENCES Team(id)
);

