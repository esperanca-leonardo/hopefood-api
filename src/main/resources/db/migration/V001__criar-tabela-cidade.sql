create table cidade (
  id int8 generated by default as identity,
  data_atualizacao timestamp not null,
  data_cadastro timestamp not null,
  nome varchar(255) not null,
  estado_id int8 not null,

  primary key (id)
);