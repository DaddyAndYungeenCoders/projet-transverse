-- Insertion de données dans la table Users
/*INSERT INTO Users (username, password, created_date)
VALUES
    ('JohnDoe', 'password123', '2024-01-01'),
    ('JaneSmith', 'securepass', '2024-01-02'),
    ('BobJohnson', 'pass123', '2024-01-03');*/

-- Insertion de données dans la table Sensor
INSERT INTO Sensor (latitude, longitude, intensity)
VALUES
    (45.7579, 4.8320,0),
    (45.7640, 4.8357,2),
    (45.7737, 4.8340,3);

-- Insertion de données dans la table FireEvent
delete from fireevent;

-- Insertion de données dans la table FireStation
delete from firestation;
SELECT setval('firestation_id_seq', 1, false);
INSERT INTO FireStation (name, address, latitude, longitude)
VALUES
    ('Centre d Incendie et de Secours', '17 rue baudin', 45.7653297, 4.9029549),
    ('CASERNE FICTIVE 1', '1 rue du hasard', 45.757098, 4.876627),
    ('CASERNE FICTIVE 2', '17 rue du random', 45.769207, 4.870679),

-- Insertion de données dans la table Team
INSERT INTO Team (fire_station_id, stamina, fire_mastery)
VALUES
    (1, 100, 10),
    (2, 100, 10),
    (3, 100, 10);

/*-- Insertion de données dans la table intervention
INSERT INTO intervention (id_fire_event, id_team, duration)
VALUES
    (1, 1, '02:30:00'::interval),
    (2, 2, '01:45:00'::interval),
    (3, 3, '03:00:00'::interval);*/
