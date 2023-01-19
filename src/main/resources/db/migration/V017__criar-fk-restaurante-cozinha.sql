alter table if exists restaurante
  add constraint fk_restaurante_cozinha
  foreign key (cozinha_id)
  references cozinha;