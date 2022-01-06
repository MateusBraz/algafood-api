set
foreign_key_checks = 0;

truncate cidade;
truncate cozinha;
truncate estado;
truncate forma_pagamento;
truncate grupo;
truncate grupo_permissao;
truncate permissao;
truncate produto;
truncate restaurante;
truncate restaurante_forma_pagamento;
truncate usuario;
truncate usuario_grupo;
truncate restaurante_usuario_responsavel;
truncate pedido;
truncate item_pedido;
truncate foto_produto;
truncate oauth_client_details;

set
foreign_key_checks = 1;

insert into cozinha (id, nome)
values (1, 'Tailandesa');
insert into cozinha (id, nome)
values (2, 'Indiana');
insert into cozinha (id, nome)
values (3, 'Argentina');
insert into cozinha (id, nome)
values (4, 'Brasileira');

insert into estado (id, nome)
values (1, 'Minas Gerais');
insert into estado (id, nome)
values (2, 'São Paulo');
insert into estado (id, nome)
values (3, 'Ceará');

insert into cidade (id, nome, estado_id)
values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id)
values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id)
values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id)
values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id)
values (5, 'Fortaleza', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,
                         endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, ativo, aberto)
values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro',
        true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto)
values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto)
values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto)
values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto)
values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto)
values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, true, true);

insert into forma_pagamento (id, descricao, data_atualizacao)
values (1, 'Cartão de crédito', utc_timestamp);
insert into forma_pagamento (id, descricao, data_atualizacao)
values (2, 'Cartão de débito', utc_timestamp);
insert into forma_pagamento (id, descricao, data_atualizacao)
values (3, 'Dinheiro', utc_timestamp);

insert into permissao (id, nome, descricao)
values (1, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissao (id, nome, descricao)
values (2, 'EDITAR_FORMAS_PAGAMENTO', 'Permite criar ou editar formas de pagamento');
insert into permissao (id, nome, descricao)
values (3, 'EDITAR_CIDADES', 'Permite criar ou editar cidades');
insert into permissao (id, nome, descricao)
values (4, 'EDITAR_ESTADOS', 'Permite criar ou editar estados');
insert into permissao (id, nome, descricao)
values (5, 'CONSULTAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite consultar usuários');
insert into permissao (id, nome, descricao)
values (6, 'EDITAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite criar ou editar usuários');
insert into permissao (id, nome, descricao)
values (7, 'EDITAR_RESTAURANTES', 'Permite criar, editar ou gerenciar restaurantes');
insert into permissao (id, nome, descricao)
values (8, 'CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
insert into permissao (id, nome, descricao)
values (9, 'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');
insert into permissao (id, nome, descricao)
values (10, 'GERAR_RELATORIOS', 'Permite gerar relatórios');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 3),
       (3, 2),
       (3, 3),
       (4, 1),
       (4, 2),
       (5, 1),
       (5, 2),
       (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, false, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Salada picante com carne grelhada',
        'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,
        1, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé',
        79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('T-Bone',
        'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,
        1, 4);

insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into grupo (id, nome)
values (1, 'Gerente'),
       (2, 'Vendedor'),
       (3, 'Secretária'),
       (4, 'Cadastrador');

insert into grupo_permissao (grupo_id, permissao_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (1, 10),
       (2, 1),
       (2, 2),
       (3, 1);

insert into usuario (id, nome, email, senha, data_cadastro)
values (1, 'Mateus Braz', 'silvabraz96@gmail.com', '$2a$12$lsW4FGpMWeqxWIQluyjbgO41YEc9wSW.5szeiicTxxCaBArXDX6zy', utc_timestamp),
       (2, 'Fabrício Ventura', 'ventura.programing@gmail.com', '$2a$12$lsW4FGpMWeqxWIQluyjbgO41YEc9wSW.5szeiicTxxCaBArXDX6zy', utc_timestamp),
       (3, 'José Souza', 'jose.aux@algafood.com', '$2a$12$lsW4FGpMWeqxWIQluyjbgO41YEc9wSW.5szeiicTxxCaBArXDX6zy', utc_timestamp),
       (4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '$2a$12$lsW4FGpMWeqxWIQluyjbgO41YEc9wSW.5szeiicTxxCaBArXDX6zy', utc_timestamp);

insert into usuario_grupo (usuario_id, grupo_id)
values (1, 1),
       (1, 2),
       (2, 2);

insert into usuario (id, nome, email, senha, data_cadastro)
values (5, 'Manoel Lima', 'manoel.loja@gmail.com', '$2a$12$lsW4FGpMWeqxWIQluyjbgO41YEc9wSW.5szeiicTxxCaBArXDX6zy', utc_timestamp);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id)
values (1, 5),
       (3, 5);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id,
                    endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, data_confirmacao, subtotal, taxa_frete, valor_total)
values (1, 'bd7f11da-aebe-43ac-9a38-e582595ad4e5', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801',
        'Brasil',
        'CRIADO', '2021-10-11 09:58:00', '2021-10-11 10:58:00', 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');


insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id,
                    endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, data_confirmacao, subtotal, taxa_frete, valor_total)
values (2, '59f794b1-33ad-48d2-b7de-6d07fead913d', 4, 2, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
        'CRIADO', '2021-10-12 09:58:00', '2021-10-12 10:58:00', 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (3, 2, 6, 1, 79, 79, 'Ao ponto');

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id,
                    endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, data_confirmacao, subtotal, taxa_frete, valor_total)
values (3, '674c69a3-c118-48d5-98d3-70ecb1eac2c2', 4, 3, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
        'CONFIRMADO', '2021-10-12 02:00:00', '2021-10-12 02:58:00', 158, 0, 158);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (4, 3, 6, 2, 158, 79, 'Ao ponto');

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id,
                    endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, data_confirmacao, subtotal, taxa_frete, valor_total)
values (4, 'e7ca5e2e-32cd-4380-8897-fcc1675e6940', 4, 4, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
        'CONFIRMADO', '2021-10-12 02:00:00', '2021-10-12 02:58:00', 237, 0, 237);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (5, 4, 6, 3, 237, 79, 'Ao ponto');

insert into oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types,
                                  web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, autoapprove)
values ('algafood-web', null, '$2a$12$XvH1cn8Jd.GJiZmiHvmPleM5Ju4cWvabD6yqYDTlxboAM1fkQ5UU6',
        'READ,WRITE', 'password', null, null, 60 * 60 * 6, 60 * 24 * 60 * 60, null);

insert into oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types,
                                  web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, autoapprove)
values ('foodanalytics', null, '$2a$12$lsW4FGpMWeqxWIQluyjbgO41YEc9wSW.5szeiicTxxCaBArXDX6zy',
        'READ,WRITE', 'authorization_code', 'http://localhost:8082', null, null, null, null);

insert into oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types,
                                  web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, autoapprove)
values ('faturamento', null, '$2a$12$lsW4FGpMWeqxWIQluyjbgO41YEc9wSW.5szeiicTxxCaBArXDX6zy',
        'READ,WRITE', 'client_credentials', null, 'CONSULTAR_PEDIDOS,GERAR_RELATORIOS', null, null, null);
