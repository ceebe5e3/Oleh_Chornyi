INSERT INTO roles VALUES (0, 'admin');
INSERT INTO roles VALUES (1, 'subscriber');

INSERT INTO users VALUES (1, 'Чорный Олег Витальевич', '1999-05-09', 'Харьков', 'olegchornyi182@gmail.com', 'oleg', 'oleg182', '0');
INSERT INTO users VALUES (2, 'Example Example Example', '1999-04-09', 'Example', 'example@gmail.com', 'example', ' example', '1');

INSERT INTO tariffs VALUES (1, 'Basic');
INSERT INTO tariffs VALUES (2, 'Normal+');
INSERT INTO tariffs VALUES (3, 'Normal');
INSERT INTO tariffs VALUES (4, 'Platinum');
INSERT INTO tariffs VALUES (5, 'Gold+');

INSERT INTO services VALUES (1, 'Internet');
INSERT INTO services VALUES (2, 'IP-TV');
INSERT INTO services VALUES (3, 'Cable TV');
INSERT INTO services VALUES (4, 'Local telephone');

INSERT INTO service_tariffs VALUES (1, 1, 1, 70.0, 'Speed: 50 Mbit/s');
INSERT INTO service_tariffs VALUES (2, 2, 2, 100.0, 'Speed: 60 Mbit/s');
INSERT INTO service_tariffs VALUES (3, 3, 3, 200.0, 'Speed: 400 Mbit/s');
INSERT INTO service_tariffs VALUES (4, 4, 4, 150.0, 'Speed: 100 Mbit/s');
INSERT INTO service_tariffs VALUES (5, 2, 5, 200.0, 'Speed: 350 Mbit/s');

INSERT INTO contracts VALUES (1, 2, 1, '2020-04-12');

INSERT INTO account VALUES (1, 2, 100.0, 'UNBLOCKED', 'UNBLOCKED');