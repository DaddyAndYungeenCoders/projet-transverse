-- Insertion de données dans la table FireStation
delete from firestation;
SELECT setval('firestation_id_seq', 1, false);
INSERT INTO FireStation (name, address, latitude, longitude)
VALUES
    ('Centre d Incendie et de Secours', '17 rue baudin', 45.7653297, 4.9029549),
    ('CASERNE FICTIVE 1', '1 rue du hasard', 45.757098, 4.876627),
    ('CASERNE FICTIVE 2', '20 avenue albert einstein', 45.782051, 4.872859),
    ('CASERNE FICTIVE 3', '17 rue du random', 45.769207, 4.870679);
