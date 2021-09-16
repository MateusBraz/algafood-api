alter table restaurante
    add ativo tinyint(1) not null after endereco_cidade_id;

update restaurante
set ativo = true;