alter table if exists usuario_grupo
  add constraint fk_usuario_grupo_grupo
  foreign key (grupo_id)
  references grupo;