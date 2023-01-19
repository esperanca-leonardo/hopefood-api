alter table if exists usuario_grupo
  add constraint fk_usuario_grupo_usuario
  foreign key (usuario_id)
  references usuario;