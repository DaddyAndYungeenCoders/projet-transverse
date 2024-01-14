-- Insertion de données dans la table FireStation
delete from firestation;
SELECT setval('firestation_id_seq', 1, false);
INSERT INTO FireStation (name, address, latitude, longitude)
VALUES
    ('Centre d Incendie et de Secours', '17 rue baudin', 45.7653297, 4.9029549),
    ('CASERNE FICTIVE 1', '1 rue du hasard', 45.757098, 4.876627),
    ('CASERNE FICTIVE 2', '20 avenue albert einstein', 45.782051, 4.872859),
    ('CASERNE FICTIVE 3', '17 rue du random', 45.769207, 4.870679);

INSERT INTO Vehicule (capacity, weight, speed, product_type, fire_mastery, id_fire_station)
VALUES
    (6, 3500, 90, 'VSAV', 5, 1),
    (5, 8000, 80, 'EPA', 8, 1),
    (4, 7000, 85, 'FPT', 7, 2),
    (6, 3000, 95, 'CAR', 6, 3),
    (5, 8500, 75, 'EPA', 8, 4),
    (4, 7500, 88, 'FPT', 7, 1),
    (6, 3200, 92, 'CAR', 6, 2),
    (5, 8100, 82, 'EPA', 8, 3);
INSERT INTO Team (chief_officer, stamina, id_firestation, is_available, current_latitude, current_longitude)
VALUES
    ('Jean Dupont', 80, 1, TRUE, 45.7653297, 4.9029549),
    ('Marie Curie', 85, 2, TRUE, 45.757098, 4.876627),
    ('Pierre Martin', 75, 3, FALSE, 45.782051, 4.872859),
    ('Lucie Durant', 90, 4, TRUE, 45.769207, 4.870679);
INSERT INTO Fireman (lastname, firstname, birthdate, fire_mastery, id_team)
VALUES
    ('Dupont', 'Jean', '1978-02-15', 9, 1),
    ('Curie', 'Marie', '1980-04-30', 8, 2),
    ('Martin', 'Pierre', '1982-07-20', 8, 3),
    ('Durant', 'Lucie', '1985-11-05', 9, 4),
    ('Lefebvre', 'Henri', '1985-06-15', 5, 1),
    ('Bernard', 'Sophie', '1990-09-21', 6, 1),
    ('Petit', 'Émile', '1988-03-11', 7, 2),
    ('Moreau', 'Clara', '1992-12-30', 6, 2),
    ('Girard', 'Nicolas', '1987-07-25', 8, 3),
    ('Leroy', 'Julie', '1991-05-19', 5, 3),
    ('Roux', 'David', '1989-11-09', 7, 4),
    ('Vincent', 'Anne', '1986-04-17', 6, 4);

