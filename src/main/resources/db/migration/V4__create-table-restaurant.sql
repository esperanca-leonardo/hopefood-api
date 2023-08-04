CREATE TABLE restaurant (
  id BIGINT AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  freight_value DECIMAL(10,2) NOT NULL,

  kitchen_id BIGINT NOT NULL,
  city_id BIGINT NOT NULL,

  address_cep VARCHAR(255) NOT NULL,
  address_public_place VARCHAR(255) NOT NULL,
  address_number VARCHAR(255),
  address_complement VARCHAR(255) NOT NULL,
  address_district VARCHAR(255) NOT NULL,

  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,

  CONSTRAINT pk_restaurant PRIMARY KEY (id),
  CONSTRAINT fk_restaurant_kitchen FOREIGN KEY (kitchen_id) REFERENCES kitchen (id),
  CONSTRAINT fk_restaurant_city FOREIGN KEY (city_id) REFERENCES city (id)
) engine=InnoDB default charset=utf8;
