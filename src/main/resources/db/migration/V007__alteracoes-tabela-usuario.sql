alter table pedido drop foreign key fk_pedido_usuario_cliente;
alter table usuario_grupo drop foreign key fk_usuario_grupo_usuario;

alter table usuario modify column id bigint(20) not null auto_increment;

alter table pedido
    add constraint fk_pedido_usuario_cliente foreign key (usuario_cliente_id) references usuario (id);
alter table usuario_grupo
    add constraint fk_usuario_grupo_usuario foreign key (usuario_id) references usuario (id);

ALTER TABLE `usuario` CHANGE COLUMN `data_criacao` `data_cadastro` DATETIME NOT NULL;
