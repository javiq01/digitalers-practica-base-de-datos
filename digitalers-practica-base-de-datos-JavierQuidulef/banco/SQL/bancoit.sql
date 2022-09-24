drop database if exists bancodigitalers;
create database if not exists bancodigitalers;

use bancodigitalers;


-- SUCURSALES ------------------------------------------------
drop table if exists sucursales;

create table if not exists sucursales (
	nombre varchar(50) not null,
	ciudad varchar(50) not null,
	
	constraint ux_nombre_sucursal unique(nombre),
	primary key (nombre)
);

select * from sucursales;



-- ADMINISTRACION --------------------------------------------------------
drop table if exists administracion;

create table if not exists administracion (
	nombre_sucursal varchar(50) not null,
	id_empleado int not null,
	nombre_empleado varchar(50) not null,
	telefono_empleado varchar(50) default null,
	id_empleadoJefe int not null,
	fecha_incorporacion date not null,
	
	primary key (nombre_sucursal, id_empleado),
	
	foreign key (nombre_sucursal) references sucursales (nombre),
	foreign key (id_empleado) references empleados (id)


);

select  * from administracion ; 
-- select nombre_empleado from administracion where id_empleadoJefe = 110011;



-- EMPLEADOS -------------------------------------------------------
drop table if exists empleados;

create table if not exists empleados (
	id int not null,
	nombre varchar(50) not null,
	telefono varchar(50) default null,
	nombre_sucursal varchar (50) not null,
	
	primary key (id),
	
	foreign key (nombre_sucursal) references sucursales(nombre);
);

select * from empleados;




-- CLIENTES -----------------------------------------------------------------------
drop table if exists clientes;

create table if not exists clientes (
	id int not null,
	nombre varchar(50) not null,
	calle varchar(50) not null,
	ciudad varchar(50) not null,
	prestamo double default null,
	id_empleado int not null,
	nombre_sucursal varchar(50) not null,
	
	primary key (id),
	
	foreign key (id_empleado) references empleados(id),
	foreign key (nombre_sucursal) references sucursales (nombre)
);

select * from clientes;



-- CUENTAS --------------------------------------------------------------------------
drop table if exists cuentas;

create table if not exists cuentas (
	numero int not null,
	tipo varchar(50) not null,
	saldo double not null,
	nombre_sucursal varchar(50),
	
	constraint ch_cuentas_tipo check(tipo in ('CUENTA_CORRIENTE', 'CAJA_AHORRO')),
	
	primary key (numero),
	
	foreign key (nombre_sucursal) references sucursales (nombre)
);

-- insert into cuentas values(101,'CUENTA_CORRIENTE',200,'ushuaia_sucursal');
select * from cuentas;



-- CLIENTES-CUENTAS -------------------------------------------------------------
drop table if exists clientes_cuentas;

create table if not exists clientes_cuentas (
	id_cliente int not null,
	numero_cuenta int not null,
	
	primary key (id_cliente, numero_cuenta),
	
	foreign key (id_cliente) references clientes(id),
	foreign key (numero_cuenta) references cuentas(numero)
);

-- insert into clientes_cuentas values (1,101);

select * from clientes_cuentas ;


