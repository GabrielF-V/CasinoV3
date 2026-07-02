CREATE TABLE maquinas(
    id_maquina bigint auto_increment primary key,
    costo int not null,
    pozo int not null,
    id_tipo bigint,
    id_cliente bigint
);