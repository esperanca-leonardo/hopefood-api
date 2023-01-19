alter table if exists produto
  add constraint fk_produto_restaurante
  foreign key (restaurante_id)
  references restaurante;