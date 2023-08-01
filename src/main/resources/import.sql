INSERT INTO kitchen (name, description) VALUES ("Italiana", "Massas, pizzas e molhos saborosos. Ingredientes frescos como azeite de oliva, tomates, queijos e ervas aromáticas. Tradição e autenticidade em cada prato.");
INSERT INTO kitchen (name, description) VALUES ("Mexicana", "Sabores picantes e vibrantes. Pimentas, feijões, milho, abacate, tomates e especiarias. Tacos, burritos, guacamole e enchiladas. Riqueza cultural em cada mordida.");

INSERT INTO state (name) VALUES ('São Paulo');
INSERT INTO state (name) VALUES ('Rio de Janeiro');

INSERT INTO city (name, state_id) VALUES ('Assis', 1);
INSERT INTO city (name, state_id) VALUES ('Bauru', 1);
INSERT INTO city (name, state_id) VALUES ('Angra dos Reis', 2);

INSERT INTO restaurant (name, kitchen_id, freight_value, cep, complement, district, number, public_place, city_id) VALUES ('Ristorante delizioso', 1, 5.99, '12345-678', 'Apto 101', 'Centro', '123', 'Rua das Flores', 1);
INSERT INTO restaurant (name, kitchen_id, freight_value, cep, complement, district, number, public_place, city_id) VALUES ('Pizzaria Bella Napoli', 1, 4.99, '98765-432', 'Casa 2', 'Jardim das Palmeiras', '456', 'Av. dos Ipês', 2);
INSERT INTO restaurant (name, kitchen_id, freight_value, cep, complement, district, number, public_place, city_id) VALUES ('Taco Loco', 2, 6.50, '54321-876', 'Sala 201', 'Vila Nova', '789', 'Rua do Comércio', 3);
INSERT INTO restaurant (name, kitchen_id, freight_value, cep, complement, district, number, public_place, city_id) VALUES ('El Sabor Auténtico', 2, 7.99, '24680-135', 'Bloco B', 'Bairro Feliz', '246', 'Travessa das Alegrias', 1);

INSERT INTO form_of_payment (description) VALUES ('Cartão de Crédito');
INSERT INTO form_of_payment (description) VALUES ('Boleto Bancário');
INSERT INTO form_of_payment (description) VALUES ('Pix');

INSERT INTO permission (name, description) VALUES ('Admin', 'Permissão de Administrador');
INSERT INTO permission (name, description) VALUES ('Usuário', 'Permissão de Usuário');

INSERT INTO restaurant_form_of_payments (restaurant_id, form_of_payments_id) VALUES (1, 1);
INSERT INTO restaurant_form_of_payments (restaurant_id, form_of_payments_id) VALUES (1, 2);
INSERT INTO restaurant_form_of_payments (restaurant_id, form_of_payments_id) VALUES (1, 3);
INSERT INTO restaurant_form_of_payments (restaurant_id, form_of_payments_id) VALUES (2, 3);
INSERT INTO restaurant_form_of_payments (restaurant_id, form_of_payments_id) VALUES (3, 2);
INSERT INTO restaurant_form_of_payments (restaurant_id, form_of_payments_id) VALUES (3, 3);
