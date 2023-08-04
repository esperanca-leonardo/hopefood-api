CREATE TABLE city (
  id BIGINT AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,

  state_id BIGINT NOT NULL,

  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,

  CONSTRAINT pk_city PRIMARY KEY (id),
  CONSTRAINT fk_city_state FOREIGN KEY (state_id) REFERENCES state (id)
) engine=InnoDB default charset=utf8;
