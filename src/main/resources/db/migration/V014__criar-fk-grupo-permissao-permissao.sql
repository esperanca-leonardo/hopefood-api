alter table if exists grupo_permissao
  add constraint fk_grupo_permissao_permissao
  foreign key (permissao_id)
  references permissao;