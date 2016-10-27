create database acme;
use acme;

create table clientes(
	id_cliente int(4) PRIMARY KEY not null AUTO_INCREMENT,
 	nombre varchar(20) not null,
	ap_paterno varchar(20) not null,
	ap_materno varchar(20) not null,
 	telefono int(15) not null,
 	e_mail varchar(40) not null,
 	RFC varchar(15) not null,
 	calle varchar(20) not null,
 	No int(5) not null,
 	colonia varchar(20) not null,
 	ciudad varchar(20) not null,
 	estado varchar(20) not null
 	)ENGINE=MyISAM DEFAULT CHARSET=utf8;
 
 create table ventas(
 	id_vetas int(4) PRIMARY KEY not null AUTO_INCREMENT,
 	fecha date not null,
 	id_cliente int(4) not null,
 	subtotal double(7,2) not null,
 	IVA double not null,
 	total double not null,
 	FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente)
 	)ENGINE=MyISAM DEFAULT CHARSET=utf8;
 
 create table productos(
	id_producto int (4) PRIMARY KEY not null AUTO_INCREMENT,
	producto varchar (30) not null,
	descripcion varchar (30) not null,
	precio_compra double not null,
	precio_venta double not null,
	existencias int(8)
	)ENGINE=MyISAM DEFAULT CHARSET= utf8;
 
 create table detalle_ventas(
 	id_detalle_venta int(4) PRIMARY KEY not null AUTO_INCREMENT,
 	id_vetas int(4) not null,
 	id_producto int(4) not null,
 	cantidad int(5) not null,
 	tatal_producto int(7) not null,
 	precio double not null,
 	FOREIGN KEY (id_vetas) REFERENCES ventas (id_vetas),
 	FOREIGN KEY (id_producto) REFERENCES productos (id_producto)
 	)ENGINE=MyISAM DEFAULT CHARSET=utf8;
 
 create table proveedores(
 	id_proveedor int(4) PRIMARY KEY not null AUTO_INCREMENT,
 	nombre varchar(30) not null,
 	RFC varchar(15) not null,
 	calle varchar(20) not null,
	No int(5) not null,
 	colonia varchar(20) not null,
 	ciudad varchar(20) not null,
 	estado varchar(20) not null,
 	nombre_contacto varchar(70) not null,
 	telefono int(15) not null,
 	e_mail varchar(40) not null
 	)ENGINE=MyISAM DEFAULT CHARSET=utf8;
 
 create table compras(
 	id_compra int(4) PRIMARY KEY not null AUTO_INCREMENT,
 	fecha date not null,
 	id_proveedor int(4) not null,
 	subtotal double not nul
 	IVA double not null,
 	total double not null,
 	FOREIGN KEY (id_proveedor) REFERENCES proveedores (id_proveedor)
 	)ENGINE=MyISAM DEFAULT CHARSET=utf8;
 
 create table detalle_compras(
 	id_detalle_compra int(4) PRIMARY KEY not null AUTO_INCREMENT,
 	id_compra int(4) not null,
 	id_producto int(4) not null,
 	cantidad int(5) not null,
 	tatal_producto int(7) not null,
 	FOREIGN KEY (id_compra) REFERENCES compras (id_compra),
 	FOREIGN KEY (id_producto) REFERENCES productos (id_producto)
 	)ENGINE=MyISAM DEFAULT CHARSET=utf8;

insert into productos values ("1","Pantalon","Nuevo","4.2","5.0","10");

