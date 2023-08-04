SET foreign_key_checks = 0;

DELETE FROM kitchen;
DELETE FROM state;
DELETE FROM city;
DELETE FROM restaurant;
DELETE FROM form_of_payment;
DELETE FROM permission;

SET foreign_key_checks = 1;

ALTER TABLE kitchen auto_increment = 1;
ALTER TABLE state auto_increment = 1;
ALTER TABLE city auto_increment = 1;
ALTER TABLE restaurant auto_increment = 1;
ALTER TABLE form_of_payment auto_increment = 1;
ALTER TABLE permission auto_increment = 1;

INSERT INTO kitchen (name, description, created_at, updated_at) VALUES ("Italiana", "Massas, pizzas e molhos saborosos. Ingredientes frescos como azeite de oliva, tomates, queijos e ervas aromáticas. Tradição e autenticidade em cada prato.", utc_timestamp, utc_timestamp);
INSERT INTO kitchen (name, description, created_at, updated_at) VALUES ("Mexicana", "Sabores picantes e vibrantes. Pimentas, feijões, milho, abacate, tomates e especiarias. Tacos, burritos, guacamole e enchiladas. Riqueza cultural em cada mordida.", utc_timestamp, utc_timestamp);

INSERT INTO state (name, created_at, updated_at) VALUES ('São Paulo', utc_timestamp, utc_timestamp);
INSERT INTO state (name, created_at, updated_at) VALUES ('Rio de Janeiro', utc_timestamp, utc_timestamp);

INSERT INTO city (name, state_id, created_at, updated_at) VALUES ('Assis', 1, utc_timestamp, utc_timestamp);
INSERT INTO city (name, state_id, created_at, updated_at) VALUES ('Bauru', 1, utc_timestamp, utc_timestamp);
INSERT INTO city (name, state_id, created_at, updated_at) VALUES ('Angra dos Reis', 2, utc_timestamp, utc_timestamp);

INSERT INTO restaurant (name, kitchen_id, freight_value, address_cep, address_complement, address_district, address_number, address_public_place, city_id, created_at, updated_at) VALUES ('Ristorante delizioso', 1, 5.99,'12345-678', 'Apto 101', 'Centro', '123', 'Rua das Flores', 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (name, kitchen_id, freight_value, address_cep, address_complement, address_district, address_number, address_public_place, city_id, created_at, updated_at) VALUES ('Pizzaria Bella Napoli', 1, 4.99, '98765-432', 'Casa 2', 'Jardim das Palmeiras', '456', 'Av. dos Ipês', 2, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (name, kitchen_id, freight_value, address_cep, address_complement, address_district, address_number, address_public_place, city_id, created_at, updated_at) VALUES ('Taco Loco', 2, 6.50, '54321-876', 'Sala 201', 'Vila Nova', '789', 'Rua do Comércio', 3, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (name, kitchen_id, freight_value, address_cep, address_complement, address_district, address_number, address_public_place, city_id, created_at, updated_at) VALUES ('El Sabor Auténtico', 2, 7.99, '24680-135', 'Bloco B', 'Bairro Feliz', '246', 'Travessa das Alegrias', 1, utc_timestamp, utc_timestamp);

INSERT INTO form_of_payment (description, created_at, updated_at) VALUES ('Cartão de Crédito', utc_timestamp, utc_timestamp);
INSERT INTO form_of_payment (description, created_at, updated_at) VALUES ('Boleto Bancário', utc_timestamp, utc_timestamp);
INSERT INTO form_of_payment (description, created_at, updated_at) VALUES ('Pix', utc_timestamp, utc_timestamp);

INSERT INTO permission (name, description, created_at, updated_at) VALUES ('Admin', 'Permissão de Administrador', utc_timestamp, utc_timestamp);
INSERT INTO permission (name, description, created_at, updated_at) VALUES ('Usuário', 'Permissão de Usuário', utc_timestamp, utc_timestamp);

INSERT INTO restaurant_form_of_payments (restaurant_id, form_of_payment_id) VALUES (1, 1);
INSERT INTO restaurant_form_of_payments (restaurant_id, form_of_payment_id) VALUES (1, 2);
INSERT INTO restaurant_form_of_payments (restaurant_id, form_of_payment_id) VALUES (1, 3);
INSERT INTO restaurant_form_of_payments (restaurant_id, form_of_payment_id) VALUES (2, 3);
INSERT INTO restaurant_form_of_payments (restaurant_id, form_of_payment_id) VALUES (3, 2);
INSERT INTO restaurant_form_of_payments (restaurant_id, form_of_payment_id) VALUES (3, 3);