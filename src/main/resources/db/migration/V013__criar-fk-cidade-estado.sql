alter table if exists cidade
  add constraint fk_cidade_estado
  foreign key (estado_id)
  references estado;