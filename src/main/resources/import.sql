INSERT INTO kitchen (name, description) VALUES ("Italiana", "Massas, pizzas e molhos saborosos. Ingredientes frescos como azeite de oliva, tomates, queijos e ervas aromáticas. Tradição e autenticidade em cada prato.");
INSERT INTO kitchen (name, description) VALUES ("Mexicana", "Sabores picantes e vibrantes. Pimentas, feijões, milho, abacate, tomates e especiarias. Tacos, burritos, guacamole e enchiladas. Riqueza cultural em cada mordida.");

INSERT INTO restaurant (name, kitchen_id, freight_value) VALUES ("Ristorante delizioso", 1, 5.99);
INSERT INTO restaurant (name, kitchen_id, freight_value) VALUES ("Pizzaria Bella Napoli", 1, 4.99);
INSERT INTO restaurant (name, kitchen_id, freight_value) VALUES ("Taco Loco", 2, 6.50);
INSERT INTO restaurant (name, kitchen_id, freight_value) VALUES ("El Sabor Auténtico", 2, 7.99);

INSERT INTO state (name) VALUES ('São Paulo');
INSERT INTO state (name) VALUES ('Rio de Janeiro');

INSERT INTO city (name, state_id) VALUES ('Assis', 1);
INSERT INTO city (name, state_id) VALUES ('Angra dos Reis', 2);

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
