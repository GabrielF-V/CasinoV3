CREATE TABLE clientes(
    id bigint auto_increment primary key,
    nombre varchar(20) not null,
    apellido varchar(20) not null,
    gmail varchar(30) not null,
    cantidad_fichas int  not null

);