CREATE TABLE permission (
  id BIGINT AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,

  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,

  CONSTRAINT pk_permission PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;