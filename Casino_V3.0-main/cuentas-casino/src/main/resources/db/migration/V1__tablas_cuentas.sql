CREATE TABLE cuentas_clientes(
    id bigint auto_increment primary key,
    nom_usuario varchar(60) not null,
    contrasena varchar(40) not null,
    cliente bigint not null
);

CREATE TABLE cuentas_empleados(
    id bigint auto_increment primary key,
    nom_usuario varchar(60) not null,
    contrasena varchar(40) not null,
    empleado bigint not null
)