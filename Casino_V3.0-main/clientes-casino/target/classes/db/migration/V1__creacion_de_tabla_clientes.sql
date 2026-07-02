CREATE TABLE clientes(
    id bigint auto_increment primary key,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    edad int not null,
    email varchar(100) not null,
    cantidad_fichas int  not null CHECK(cantidad_fichas > 0)

);