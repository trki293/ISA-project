INSERT INTO system_parameters (id, booking_fee, income_from_reservations, points_for_clients, points_for_providers, threshold_for_silver, threshold_for_gold, discount_for_regular, discount_for_silver, discount_for_gold) values (nextval('my_seq_system_parameters'),10.0, 0.0, 3, 3, 30, 60, 0.0, 25.0, 50.0);

INSERT INTO authority (id, name) values (nextval('my_seq_authority'), 'ROLE_CLIENT');
INSERT INTO authority (id, name) values (nextval('my_seq_authority'), 'ROLE_BOATOWN');
INSERT INTO authority (id, name) values (nextval('my_seq_authority'), 'ROLE_COTTAGEOWN');
INSERT INTO authority (id, name) values (nextval('my_seq_authority'), 'ROLE_INSTRUCTOR');
INSERT INTO authority (id, name) values (nextval('my_seq_authority'), 'ROLE_ADMINSYS');

INSERT INTO users (id, email, password, first_name, last_name, phone_number, street_name, street_number, city, country, type_of_user, enabled_login, deleted) values (nextval('my_seq_users'), 'dtrkulja5@gmail.com', '$2a$12$2WXzEIfwgCSeknB1VH1/g.vTLyKgwK074C2Kn5GS7YlUs7L.fmtHO', 'Dusan', 'Trkulja', '+387659846756', 'Djure Jaksica', '6a', 'Novi Sad', 'Srbija', 1, true, false);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, street_name, street_number, city, country, type_of_user, enabled_login, deleted) values (nextval('my_seq_users'), 'dtrkulja56@gmail.com', '$2a$12$2WXzEIfwgCSeknB1VH1/g.vTLyKgwK074C2Kn5GS7YlUs7L.fmtHO', 'Pavle', 'Pavlovic', '+387659846765', 'Vojvode Zivojina Misica', '6', 'Beograd', 'Srbija', 2, true, false);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, street_name, street_number, city, country, type_of_user, enabled_login, deleted) values (nextval('my_seq_users'), 'dtrkulja5678@gmail.com', '$2a$12$2WXzEIfwgCSeknB1VH1/g.vTLyKgwK074C2Kn5GS7YlUs7L.fmtHO', 'Konstantin', 'Lukic', '+387659848765', 'Vojvode Stepe Stepanovica', '53', 'Nis', 'Srbija', 4, true, false);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, street_name, street_number, city, country, type_of_user, enabled_login, deleted) values (nextval('my_seq_users'), 'dtrkulja8765@gmail.com', '$2a$12$2WXzEIfwgCSeknB1VH1/g.vTLyKgwK074C2Kn5GS7YlUs7L.fmtHO', 'Milan', 'Joksimovic', '+387653846756', 'Vaska Pope', '12', 'Kragujevac', 'Srbija', 0, true, false);
INSERT INTO users (id, email, password, first_name, last_name, phone_number, street_name, street_number, city, country, type_of_user, enabled_login, deleted) values (nextval('my_seq_users'), 'dusan.trkulja999@gmail.com', '$2a$12$2WXzEIfwgCSeknB1VH1/g.vTLyKgwK074C2Kn5GS7YlUs7L.fmtHO', 'Petar', 'Novakovic', '+387653899756', 'Kralja Milana', '22', 'Kraljevo', 'Srbija', 3, true, false);

INSERT INTO users_authorities (users_id, authorities_id) values (1, 2);
INSERT INTO users_authorities (users_id, authorities_id) values (2, 3);
INSERT INTO users_authorities (users_id, authorities_id) values (3, 4);
INSERT INTO users_authorities (users_id, authorities_id) values (4, 5);
INSERT INTO users_authorities (users_id, authorities_id) values (5, 1);

INSERT INTO boat_owners (id, loyalty_points, statuse_of_user) values (1, 0, 0);

INSERT INTO cottage_owners (id, loyalty_points, statuse_of_user) values (2, 0, 0);

INSERT INTO instructors (id, loyalty_points, statuse_of_user) values (3, 0, 0);

INSERT INTO system_admins (id, is_first_login) values(4, false);

INSERT INTO clients (id, penalty_points, loyalty_points, statuse_of_user) values (5, 0, 0, 0);

INSERT INTO boats (id, title, type_of_boat, length, average_grade, deleted, engine_number, engine_power, max_speed, street_name, street_number, city, country, promotional_description, pictures_paths, capacity, rules_of_conduct, price_per_hour, percentage_of_earnings_when_canceling, owner_of_boat_id) values (nextval('my_seq_boat'), 'Brod Galeb', 0, 20.0 , 5.0 , false, 3, 30000, 80, 'Petra II Petrovica', '1', 'Novi Sad', 'Srbija', 'Nesto bezveze', 'Za sad nesto bezveze', 10, 'Gluposti razno-razne', 1000.0, 0.0, 1);
INSERT INTO boats (id, title, type_of_boat, length, average_grade, deleted, engine_number, engine_power, max_speed, street_name, street_number, city, country, promotional_description, pictures_paths, capacity, rules_of_conduct, price_per_hour, percentage_of_earnings_when_canceling, owner_of_boat_id) values (nextval('my_seq_boat'), 'Brod Verige', 1, 25.0 , 0.0 , false, 2, 20000, 60, 'Petra II Petrovica', '1', 'Novi Sad', 'Srbija', 'Nesto bezveze', 'Za sad nesto bezveze', 15, 'Gluposti razno-razne', 1500.0, 0.0, 1);

INSERT INTO cottages (id, title, street_name, street_number, city, country, promotional_description, pictures_paths, number_of_rooms, number_of_beds, rules_of_conduct, price_per_night, average_grade, deleted, owner_of_cottage_id) values (nextval('my_seq_cottage'), 'Vikendica Jahorina', 'Pape Pavla', '19', 'Novi Sad', 'Srbija','Nesto bezveze', 'Za sad nesto bezveze', 2, 4, 'Gluposti razno-razne', 12000.0, 4.0, false, 2);
INSERT INTO cottages (id, title, street_name, street_number, city, country, promotional_description, pictures_paths, number_of_rooms, number_of_beds, rules_of_conduct, price_per_night, average_grade, deleted, owner_of_cottage_id) values (nextval('my_seq_cottage'), 'Vikendica Zlatiborka', 'Marsala Tita', '9', 'Novi Sad', 'Srbija','Nesto bezveze', 'Za sad nesto bezveze', 1, 3, 'Gluposti razno-razne', 15000.0, 0.0, false, 2);

INSERT INTO instructions (id, title, street_name, street_number, city, country, promotional_description, pictures_paths, instructor_biography, max_number_of_people, average_grade, deleted, rules_of_conduct, price_per_hour, percentage_of_earnings_when_canceling, instructor_id) values (nextval('my_seq_instructions'), 'Pecanje na Dunavu', 'Petra II Petrovica', '1', 'Novi Sad', 'Srbija', 'Nesto bezveze', 'Za sad nesto bezveze', 'Neke gluposti', 6, 4.0, false, 'Gluposti razno-razne', 700.0, 0.0, 3);
INSERT INTO instructions (id, title, street_name, street_number, city, country, promotional_description, pictures_paths, instructor_biography, max_number_of_people, average_grade, deleted, rules_of_conduct, price_per_hour, percentage_of_earnings_when_canceling, instructor_id) values (nextval('my_seq_instructions'), 'Pecanje na Savi', 'Aleksandra I Karadjordjevica', '10', 'Beograd', 'Srbija', 'Nesto bezveze', 'Za sad nesto bezveze', 'Neke gluposti', 4, 0.0, false, 'Gluposti razno-razne', 850.0, 0.0, 3);

INSERT INTO availability_periods (id, begin_period, end_period, type_of_availability_period) values (nextval('my_seq_availability_period'), '2021-06-08 00:00', '2026-06-08 23:59', 0);
INSERT INTO availability_periods (id, begin_period, end_period, type_of_availability_period) values (nextval('my_seq_availability_period'), '2021-06-08 00:00', '2026-06-08 23:59', 0);
INSERT INTO availability_periods (id, begin_period, end_period, type_of_availability_period) values (nextval('my_seq_availability_period'), '2021-06-08 00:00', '2026-06-08 23:59', 1);
INSERT INTO availability_periods (id, begin_period, end_period, type_of_availability_period) values (nextval('my_seq_availability_period'), '2021-06-08 00:00', '2026-06-08 23:59', 1);
INSERT INTO availability_periods (id, begin_period, end_period, type_of_availability_period) values (nextval('my_seq_availability_period'), '2021-06-08 00:00', '2026-06-08 23:59', 2);
INSERT INTO availability_periods (id, begin_period, end_period, type_of_availability_period) values (nextval('my_seq_availability_period'), '2021-06-08 00:00', '2026-06-08 23:59', 2);

INSERT INTO boat_availability_periods (id, boat_for_availability_period_id) values (1, 1);
INSERT INTO boat_availability_periods (id, boat_for_availability_period_id) values (2, 2);

INSERT INTO cottage_availability_periods (id, cottage_for_availability_period_id) values (3, 1);
INSERT INTO cottage_availability_periods (id, cottage_for_availability_period_id) values (4, 2);

INSERT INTO instructions_availability_periods (id, instructions_for_availability_period_id) values (5, 1);
INSERT INTO instructions_availability_periods (id, instructions_for_availability_period_id) values (6, 2);

INSERT INTO quick_bookings (id, time_of_begining_reservation, time_of_ending_reservation, max_number_of_person, total_price, type_of_quick_booking, status_of_quick_booking, client_for_quick_booking_id) values (nextval('my_seq_quick_booking'), '2022-02-15 12:00', '2022-02-15 14:00', 5, 1500.0, 0, 0,null);
INSERT INTO quick_bookings (id, time_of_begining_reservation, time_of_ending_reservation, max_number_of_person, total_price, type_of_quick_booking, status_of_quick_booking, client_for_quick_booking_id) values (nextval('my_seq_quick_booking'), '2022-03-16 12:00', '2022-03-16 15:00', 5, 3500.0, 0, 0,null);
INSERT INTO quick_bookings (id, time_of_begining_reservation, time_of_ending_reservation, max_number_of_person, total_price, type_of_quick_booking, status_of_quick_booking, client_for_quick_booking_id) values (nextval('my_seq_quick_booking'), '2022-04-17 12:00', '2022-04-24 12:00', 5, 75000.0, 1, 0,null);
INSERT INTO quick_bookings (id, time_of_begining_reservation, time_of_ending_reservation, max_number_of_person, total_price, type_of_quick_booking, status_of_quick_booking, client_for_quick_booking_id) values (nextval('my_seq_quick_booking'), '2022-05-18 12:00', '2022-05-28 12:00', 5, 130000.0, 1, 0,null);
INSERT INTO quick_bookings (id, time_of_begining_reservation, time_of_ending_reservation, max_number_of_person, total_price, type_of_quick_booking, status_of_quick_booking, client_for_quick_booking_id) values (nextval('my_seq_quick_booking'), '2022-06-19 12:00', '2022-06-19 16:00', 5, 2300.0, 2, 0,null);
INSERT INTO quick_bookings (id, time_of_begining_reservation, time_of_ending_reservation, max_number_of_person, total_price, type_of_quick_booking, status_of_quick_booking, client_for_quick_booking_id) values (nextval('my_seq_quick_booking'), '2022-07-20 12:00', '2022-07-20 17:00', 5, 3700.0, 2, 0,null);

INSERT INTO boat_quick_bookings (id,boat_for_quick_reservation_id) values (1, 1);
INSERT INTO boat_quick_bookings (id,boat_for_quick_reservation_id) values (2, 2);

INSERT INTO cottage_quick_bookings (id,cottage_for_quick_reservation_id) values (3, 1);
INSERT INTO cottage_quick_bookings (id,cottage_for_quick_reservation_id) values (4, 2);

INSERT INTO instructions_quick_bookings (id, instructions_for_quick_reservation_id) values (5, 1);
INSERT INTO instructions_quick_bookings (id, instructions_for_quick_reservation_id) values (6, 2);

INSERT INTO additional_services (id, name) values (nextval('my_seq_additional_services'), 'Pet friendly');
INSERT INTO additional_services (id, name) values (nextval('my_seq_additional_services'), 'Mini bar');
INSERT INTO additional_services (id, name) values (nextval('my_seq_additional_services'), 'WiFi');
INSERT INTO additional_services (id, name) values (nextval('my_seq_additional_services'), 'Parking');
INSERT INTO additional_services (id, name) values (nextval('my_seq_additional_services'), 'Boat posess');
INSERT INTO additional_services (id, name) values (nextval('my_seq_additional_services'), 'Own boat');

INSERT INTO boats_additional_services(boat_id, additional_services_id) values (1,1);
INSERT INTO boats_additional_services(boat_id, additional_services_id) values (2,2);

INSERT INTO cottages_additional_services(cottage_id, additional_services_id) values (1,3);
INSERT INTO cottages_additional_services(cottage_id, additional_services_id) values (2,4);

INSERT INTO instructions_additional_services(instructions_id, additional_services_id) values (1,3);
INSERT INTO instructions_additional_services(instructions_id, additional_services_id) values (2,4);

INSERT INTO reservations (id, time_of_begining_reservation, time_of_ending_reservation, number_of_person, total_price, status_of_reservation, client_for_reservation_id, type_of_reservation) values (nextval('my_seq_reservation'),'2021-10-15 12:00','2021-10-25 12:00', 2, 120000.0, 1, 5, 1);
INSERT INTO reservations (id, time_of_begining_reservation, time_of_ending_reservation, number_of_person, total_price, status_of_reservation, client_for_reservation_id, type_of_reservation) values (nextval('my_seq_reservation'),'2022-01-15 12:00','2022-01-20 12:00', 2, 60000.0, 2, 5, 1);
INSERT INTO reservations (id, time_of_begining_reservation, time_of_ending_reservation, number_of_person, total_price, status_of_reservation, client_for_reservation_id, type_of_reservation) values (nextval('my_seq_reservation'),'2022-03-10 12:00','2022-03-13 12:00', 2, 36000.0, 0, 5, 1);
INSERT INTO reservations (id, time_of_begining_reservation, time_of_ending_reservation, number_of_person, total_price, status_of_reservation, client_for_reservation_id, type_of_reservation) values (nextval('my_seq_reservation'),'2021-10-15 12:00','2021-10-15 13:00', 2, 1000.0, 3, 5, 0);
INSERT INTO reservations (id, time_of_begining_reservation, time_of_ending_reservation, number_of_person, total_price, status_of_reservation, client_for_reservation_id, type_of_reservation) values (nextval('my_seq_reservation'),'2022-01-15 12:00','2022-01-15 14:00', 2, 2000.0, 2, 5, 0);
INSERT INTO reservations (id, time_of_begining_reservation, time_of_ending_reservation, number_of_person, total_price, status_of_reservation, client_for_reservation_id, type_of_reservation) values (nextval('my_seq_reservation'),'2022-03-10 12:00','2022-03-10 14:30', 2, 2500.0, 0, 5, 0);
INSERT INTO reservations (id, time_of_begining_reservation, time_of_ending_reservation, number_of_person, total_price, status_of_reservation, client_for_reservation_id, type_of_reservation) values (nextval('my_seq_reservation'),'2021-10-15 12:00','2021-10-15 13:00', 2, 1000.0, 3, 5, 2);
INSERT INTO reservations (id, time_of_begining_reservation, time_of_ending_reservation, number_of_person, total_price, status_of_reservation, client_for_reservation_id, type_of_reservation) values (nextval('my_seq_reservation'),'2022-01-15 12:00','2022-01-15 14:00', 2, 2000.0, 1, 5, 2);
INSERT INTO reservations (id, time_of_begining_reservation, time_of_ending_reservation, number_of_person, total_price, status_of_reservation, client_for_reservation_id, type_of_reservation) values (nextval('my_seq_reservation'),'2022-03-10 12:00','2022-03-10 14:30', 2, 2500.0, 0, 5, 2);

INSERT INTO cottage_reservations (id, cottage_for_reservation_id) values (1,1);
INSERT INTO cottage_reservations (id, cottage_for_reservation_id) values (2,1);
INSERT INTO cottage_reservations (id, cottage_for_reservation_id) values (3,1);

INSERT INTO boat_reservations (id, boat_for_reservation_id) values (4,1);
INSERT INTO boat_reservations (id, boat_for_reservation_id) values (5,1);
INSERT INTO boat_reservations (id, boat_for_reservation_id) values (6,1);

INSERT INTO instructions_reservations (id, instructions_for_reservation_id) values (7,1);
INSERT INTO instructions_reservations (id, instructions_for_reservation_id) values (8,1);
INSERT INTO instructions_reservations (id, instructions_for_reservation_id) values (9,1);

INSERT INTO reviews (id, rating, content, client_who_evaluating_id, reservation_being_evaluated_id, published, status_of_review, type_of_review) values (nextval('my_seq_review'), 5, 'Nesto', 5, 1, true, 0, 0);
INSERT INTO reviews (id, rating, content, client_who_evaluating_id, reservation_being_evaluated_id, published, status_of_review, type_of_review) values (nextval('my_seq_review'), 4, 'Nesto', 5, 4, true, 1, 1);
INSERT INTO reviews (id, rating, content, client_who_evaluating_id, reservation_being_evaluated_id, published, status_of_review, type_of_review) values (nextval('my_seq_review'), 4, 'Nesto', 5, 7, true, 2, 2);

INSERT INTO cottage_reviews (id, cottage_for_review_id) values(1, 1);

INSERT INTO boat_reviews (id, boat_for_review_id) values (2, 1);

INSERT INTO instructions_reviews (id, instructions_for_review_id) values (3, 1);
