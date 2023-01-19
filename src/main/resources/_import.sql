insert into cozinha (id, nome, data_cadastro, data_atualizacao) values (1, 'Tailandesa', current_timestamp, current_timestamp);
insert into cozinha (id, nome, data_cadastro, data_atualizacao) values (2, 'Indiana', current_timestamp, current_timestamp);

insert into estado (id, nome, data_cadastro, data_atualizacao) values (1, 'Minas Gerais', current_timestamp, current_timestamp);
insert into estado (id, nome, data_cadastro, data_atualizacao) values (2, 'São Paulo', current_timestamp, current_timestamp);
insert into estado (id, nome, data_cadastro, data_atualizacao) values (3, 'Ceará', current_timestamp, current_timestamp);

insert into cidade (id, nome, estado_id, data_cadastro, data_atualizacao) values (1, 'Uberlândia', 1, current_timestamp, current_timestamp);
insert into cidade (id, nome, estado_id, data_cadastro, data_atualizacao) values (2, 'Belo Horizonte', 1, current_timestamp, current_timestamp);
insert into cidade (id, nome, estado_id, data_cadastro, data_atualizacao) values (3, 'São Paulo', 2, current_timestamp, current_timestamp);
insert into cidade (id, nome, estado_id, data_cadastro, data_atualizacao) values (4, 'Campinas', 2, current_timestamp, current_timestamp);
insert into cidade (id, nome, estado_id, data_cadastro, data_atualizacao) values (5, 'Fortaleza', 3, current_timestamp, current_timestamp);

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, current_timestamp, current_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, current_timestamp, current_timestamp);

insert into forma_pagamento (id, descricao, data_cadastro, data_atualizacao) values (1, 'Cartão de crédito', current_timestamp, current_timestamp);
insert into forma_pagamento (id, descricao, data_cadastro, data_atualizacao) values (2, 'Cartão de débito', current_timestamp, current_timestamp);
insert into forma_pagamento (id, descricao, data_cadastro, data_atualizacao) values (3, 'Dinheiro', current_timestamp, current_timestamp);

insert into permissao (id, nome, descricao, data_cadastro, data_atualizacao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas', current_timestamp, current_timestamp);
insert into permissao (id, nome, descricao, data_cadastro, data_atualizacao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas', current_timestamp, current_timestamp);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 3);
