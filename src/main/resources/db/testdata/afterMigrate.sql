alter table cidade disable trigger all;
alter table cozinha disable trigger all;
alter table estado disable trigger all;
alter table forma_pagamento disable trigger all;
alter table grupo disable trigger all;
alter table grupo_permissao disable trigger all;
alter table permissao disable trigger all;
alter table produto disable trigger all;
alter table restaurante disable trigger all;
alter table restaurante_forma_pagamento disable trigger all;
alter table usuario disable trigger all;
alter table usuario_grupo disable trigger all;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;

alter sequence cidade_id_seq restart with 1;
alter sequence cozinha_id_seq restart with 1;
alter sequence estado_id_seq restart with 1;
alter sequence forma_pagamento_id_seq restart with 1;
alter sequence grupo_id_seq restart with 1;
alter sequence permissao_id_seq restart with 1;
alter sequence produto_id_seq restart with 1;
alter sequence restaurante_id_seq restart with 1;
alter sequence usuario_id_seq restart with 1;

insert into cozinha (nome, data_atualizacao, data_cadastro) values ('Tailandesa', current_timestamp, current_timestamp);
insert into cozinha (nome, data_atualizacao, data_cadastro) values ('Indiana', current_timestamp, current_timestamp);
insert into cozinha (nome, data_atualizacao, data_cadastro) values ('Argentina', current_timestamp, current_timestamp);
insert into cozinha (nome, data_atualizacao, data_cadastro) values ('Brasileira', current_timestamp, current_timestamp);
insert into cozinha (nome, data_atualizacao, data_cadastro) values ('Japonesa', current_timestamp, current_timestamp);

insert into estado (nome, data_atualizacao, data_cadastro) values ('Goiás', current_timestamp, current_timestamp);
insert into estado (nome, data_atualizacao, data_cadastro) values ('São Paulo', current_timestamp, current_timestamp);
insert into estado (nome, data_atualizacao, data_cadastro) values ('Minas Gerais', current_timestamp, current_timestamp);

insert into cidade (nome, estado_id, data_atualizacao, data_cadastro) values ('Goiânia', 1, current_timestamp, current_timestamp);
insert into cidade (nome, estado_id, data_atualizacao, data_cadastro) values ('São Paulo', 1, current_timestamp, current_timestamp);
insert into cidade (nome, estado_id, data_atualizacao, data_cadastro) values ('Belo Horizonte', 2, current_timestamp, current_timestamp);
insert into cidade (nome, estado_id, data_atualizacao, data_cadastro) values ('Campinas', 2, current_timestamp, current_timestamp);
insert into cidade (nome, estado_id, data_atualizacao, data_cadastro) values ('Uberlândia', 3, current_timestamp, current_timestamp);

insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Thai Gourmet', 10, 1, current_timestamp, current_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery', 9.50, 1, current_timestamp, current_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Tuk Tuk Comida Indiana', 15, 2, current_timestamp, current_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Java Steakhouse', 12, 3, current_timestamp, current_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Lanchonete do Tio Sam', 11, 4, current_timestamp, current_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Bar da Maria', 6, 4, current_timestamp, current_timestamp);

insert into forma_pagamento (descricao, data_atualizacao, data_cadastro) values ('Cartão de crédito', current_timestamp, current_timestamp);
insert into forma_pagamento (descricao, data_atualizacao, data_cadastro) values ('Cartão de débito', current_timestamp, current_timestamp);
insert into forma_pagamento (descricao, data_atualizacao, data_cadastro) values ('Dinheiro', current_timestamp, current_timestamp);

insert into permissao (nome, descricao, data_atualizacao, data_cadastro) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas', current_timestamp, current_timestamp);
insert into permissao (nome, descricao, data_atualizacao, data_cadastro) values ('EDITAR_COZINHAS', 'Permite editar cozinhas', current_timestamp, current_timestamp);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id, data_atualizacao, data_cadastro) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_atualizacao, data_cadastro) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_atualizacao, data_cadastro) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, true, 2, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_atualizacao, data_cadastro) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_atualizacao, data_cadastro) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_atualizacao, data_cadastro) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true, 4, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_atualizacao, data_cadastro) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, true, 4, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_atualizacao, data_cadastro) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 5, current_timestamp, current_timestamp);
insert into produto (nome, descricao, preco, ativo, restaurante_id, data_atualizacao, data_cadastro) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, 6, current_timestamp, current_timestamp);

alter table cidade enable trigger all;
alter table cozinha enable trigger all;
alter table estado enable trigger all;
alter table forma_pagamento enable trigger all;
alter table grupo enable trigger all;
alter table grupo_permissao enable trigger all;
alter table permissao enable trigger all;
alter table produto enable trigger all;
alter table restaurante enable trigger all;
alter table restaurante_forma_pagamento enable trigger all;
alter table usuario enable trigger all;
alter table usuario_grupo enable trigger all;