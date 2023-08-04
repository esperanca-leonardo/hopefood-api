CREATE TABLE restaurant_form_of_payments (
  restaurant_id BIGINT NOT NULL,
  form_of_payment_id BIGINT NOT NULL,

  CONSTRAINT fk_restaurant_form_of_payment_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
  CONSTRAINT fk_restaurant_form_of_payment_form_of_payment_id FOREIGN KEY (form_of_payment_id) REFERENCES form_of_payment (id)
) engine=InnoDB default charset=utf8;