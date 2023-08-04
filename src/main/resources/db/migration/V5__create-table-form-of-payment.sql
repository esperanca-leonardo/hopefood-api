CREATE TABLE form_of_payment (
  id BIGINT AUTO_INCREMENT,
  description VARCHAR(255) NOT NULL,

  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,

  CONSTRAINT pk_form_of_payment PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;