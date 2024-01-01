-- Insertion de données dans la table Users
INSERT INTO Users (username, password, created_date)
VALUES
    ('JohnDoe', 'password123', '2024-01-01'),
    ('JaneSmith', 'securepass', '2024-01-02'),
    ('BobJohnson', 'pass123', '2024-01-03');

-- Insertion de données dans la table Sensor
INSERT INTO Sensor (latitude, longitude)
VALUES
    (45.7579, 4.8320),
    (45.7640, 4.8357),
    (45.7737, 4.8340);

INSERT INTO FireEvent (latitude, longitude, real_intensity, start_date, end_date, is_real)
VALUES
    (45.7579, 4.8320, 75, '2024-01-05', '2024-01-06', TRUE),
    (45.7640, 4.8357, 60, '2024-01-07', '2024-01-08', FALSE),)
    (45.7737, 4.8340, 80, '2024-01-09', '2024-01-10', TRUE);

-- Insertion de données dans la table detects
INSERT INTO detects (id_sensor, id_fire_event, intensity)
VALUES
    (1, 1, 70.5),
    (2, 2, 55.8),
    (3, 3, 78.2);

-- Insertion de données dans la table Historique
INSERT INTO Historique (id_user, id_fire_event, date_creation)
VALUES
    (1, 1, '2024-01-11'),
    (2, 2, '2024-01-12'),
    (3, 3, '2024-01-13');

-- Insertion de données dans la table FireStation
INSERT INTO FireStation (name, address, latitude, longitude)
VALUES
    ('FireStation 1', '123 Main St', 34.0522, -118.2437),
    ('FireStation 2', '456 Oak St', 40.7128, -74.0060),
    ('FireStation 3', '789 Pine St', 41.8781, -87.6298);

-- Insertion de données dans la table Team
INSERT INTO Team (fire_station_id, stamina)
VALUES
    (1, 100),
    (2, 90),
    (3, 80);

-- Insertion de données dans la table intervention
INSERT INTO intervention (id_fire_event, id_team, duration)
VALUES
    (1, 1, '02:30:00'),
    (2, 2, '01:45:00'),
    (3, 3, '03:00:00');