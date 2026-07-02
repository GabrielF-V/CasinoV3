CREATE TABLE mesas(
    id_mesa bigint auto_increment primary key,
    id_juego bigint not null,
    id_cliente bigint,
    id_empleado bigint
);