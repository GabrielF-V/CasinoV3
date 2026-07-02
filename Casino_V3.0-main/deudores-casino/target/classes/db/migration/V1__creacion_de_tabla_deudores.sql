CREATE TABLE deudores(
    id_deudor bigint auto_increment primary key,
    cant_deuda int not null CHECK(cant_deuda >= 0),
    ban BOOLEAN not null,
    cliente bigint
);