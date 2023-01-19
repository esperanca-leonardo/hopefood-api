alter table if exists grupo_permissao
  add constraint fk_grupo_permissao_grupo
  foreign key (grupo_id)
  references grupo;