CREATE TABLE empleados(
    id bigint auto_increment primary key,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    gmail varchar(50) not null,
    puesto_codigo varchar(20) not null
)