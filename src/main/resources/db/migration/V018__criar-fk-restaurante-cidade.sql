alter table if exists restaurante
  add constraint fk_restaurante_cidade
  foreign key (endereco_cidade_id)
  references cidade;