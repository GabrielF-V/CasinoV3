CREATE TABLE colores(
    nro_color bigint auto_increment primary key,
    nombre varchar(80) not null,
    valor int not null CHECK(valor > 0),
    descripcion varchar(150) not null
);