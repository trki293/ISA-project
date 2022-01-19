INSERT INTO authority (id, name) values (nextval('my_seq_authority'), 'ROLE_CLIENT');
INSERT INTO authority (id, name) values (nextval('my_seq_authority'), 'ROLE_BOATOWN');
INSERT INTO authority (id, name) values (nextval('my_seq_authority'), 'ROLE_COTTAGEOWN');
INSERT INTO authority (id, name) values (nextval('my_seq_authority'), 'ROLE_INSTRUCTOR');
INSERT INTO authority (id, name) values (nextval('my_seq_authority'), 'ROLE_ADMINSYS');

INSERT INTO users (id, email, password, first_name, last_name, phone_number, street_name, street_number, city, country, type_of_user, enabled_login, deleted) values (nextval('my_seq_users'), 'dtrkulja5@gmail.com', '$2a$12$2WXzEIfwgCSeknB1VH1/g.vTLyKgwK074C2Kn5GS7YlUs7L.fmtHO', 'Dusan', 'Trkulja', '+387659846756', 'Djure Jaksica', '6a', 'Novi Sad', 'Srbija', 1, true, false);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, street_name, street_number, city, country, type_of_user, enabled_login, deleted) values (nextval('my_seq_users'), 'dtrkulja56@gmail.com', '$2a$12$2WXzEIfwgCSeknB1VH1/g.vTLyKgwK074C2Kn5GS7YlUs7L.fmtHO', 'Pavle', 'Pavlovic', '+387659846765', 'Vojvode Zivojina Misica', '6', 'Beograd', 'Srbija', 2, true, false);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, street_name, street_number, city, country, type_of_user, enabled_login, deleted) values (nextval('my_seq_users'), 'dtrkulja5678@gmail.com', '$2a$12$2WXzEIfwgCSeknB1VH1/g.vTLyKgwK074C2Kn5GS7YlUs7L.fmtHO', 'Konstantin', 'Lukic', '+387659848765', 'Vojvode Stepe Stepanovica', '53', 'Nis', 'Srbija', 4, true, false);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, street_name, street_number, city, country, type_of_user, enabled_login, deleted) values (nextval('my_seq_users'), 'dtrkulja8765@gmail.com', '$2a$12$2WXzEIfwgCSeknB1VH1/g.vTLyKgwK074C2Kn5GS7YlUs7L.fmtHO', 'Milan', 'Joksimovic', '+387653846756', 'Vaska Pope', '12', 'Kragujevac', 'Srbija', 1, true, false);

INSERT INTO boat_owners (id, loyalty_points, statuse_of_user) values (1, 0, 0);

INSERT INTO cottage_owners (id, loyalty_points, statuse_of_user) values (2, 0, 0);

INSERT INTO instructors (id, loyalty_points, statuse_of_user) values (3, 0, 0);

INSERT INTO system_admins (id, is_first_login) values(4, false);

INSERT INTO boats (id, title, type_of_boat, length, average_grade, deleted, engine_number, engine_power, max_speed, street_name, street_number, city, country, promotional_description, pictures_paths, capacity, rules_of_conduct, price_per_hour, percentage_of_earnings_when_canceling, owner_of_boat_id) values (nextval('my_seq_boat'), 'Brod Galeb', 0, 20.0 , 0.0 , false, 3, 30000, 80, 'Petra II Petrovica', '1', 'Novi Sad', 'Srbija', 'Nesto bezveze', 'Za sad nesto bezveze', 10, 'Gluposti razno-razne', 1000.0, 0.0, 1);
INSERT INTO boats (id, title, type_of_boat, length, average_grade, deleted, engine_number, engine_power, max_speed, street_name, street_number, city, country, promotional_description, pictures_paths, capacity, rules_of_conduct, price_per_hour, percentage_of_earnings_when_canceling, owner_of_boat_id) values (nextval('my_seq_boat'), 'Brod Verige', 1, 25.0 , 0.0 , false, 2, 20000, 60, 'Petra II Petrovica', '1', 'Novi Sad', 'Srbija', 'Nesto bezveze', 'Za sad nesto bezveze', 15, 'Gluposti razno-razne', 1500.0, 0.0, 1);

INSERT INTO cottages (id, title, street_name, street_number, city, country, promotional_description, pictures_paths, number_of_rooms, number_of_beds, rules_of_conduct, price_per_night, average_grade, deleted, owner_of_cottage_id) values (nextval('my_seq_cottage'), 'Vikendica Jahorina', 'Pape Pavla', '19', 'Novi Sad', 'Srbija','Nesto bezveze', 'Za sad nesto bezveze', 2, 4, 'Gluposti razno-razne', 12000.0, 0.0, false, 2);
INSERT INTO cottages (id, title, street_name, street_number, city, country, promotional_description, pictures_paths, number_of_rooms, number_of_beds, rules_of_conduct, price_per_night, average_grade, deleted, owner_of_cottage_id) values (nextval('my_seq_cottage'), 'Vikendica Zlatiborka', 'Marsala Tita', '9', 'Novi Sad', 'Srbija','Nesto bezveze', 'Za sad nesto bezveze', 1, 3, 'Gluposti razno-razne', 15000.0, 0.0, false, 2);

INSERT INTO instructions (id, title, street_name, street_number, city, country, promotional_description, pictures_paths, instructor_biography, max_number_of_people, average_grade, deleted, rules_of_conduct, price_per_hour, percentage_of_earnings_when_canceling, instructor_id) values (nextval('my_seq_instructions'), 'Pecanje na Dunavu', 'Petra II Petrovica', '1', 'Novi Sad', 'Srbija', 'Nesto bezveze', 'Za sad nesto bezveze', 'Neke gluposti', 6, 0.0, false, 'Gluposti razno-razne', 700.0, 0.0, 3);
INSERT INTO instructions (id, title, street_name, street_number, city, country, promotional_description, pictures_paths, instructor_biography, max_number_of_people, average_grade, deleted, rules_of_conduct, price_per_hour, percentage_of_earnings_when_canceling, instructor_id) values (nextval('my_seq_instructions'), 'Pecanje na Savi', 'Aleksandra I Karadjordjevica', '10', 'Beograd', 'Srbija', 'Nesto bezveze', 'Za sad nesto bezveze', 'Neke gluposti', 4, 0.0, false, 'Gluposti razno-razne', 850.0, 0.0, 3);