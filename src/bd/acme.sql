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
 	);
 
create table ventas(
 	id_ventas int(4) PRIMARY KEY not null AUTO_INCREMENT,
 	fecha datetime not null,
 	id_cliente int(4) not null,
 	subtotal double not null,
 	IVA double not null,
 	total double not null,
 	FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente)
 	)ENGINE=InnoDB;

 create table proveedores(
 	id_proveedor int(4) PRIMARY KEY not null AUTO_INCREMENT,
 	nombre varchar(30) not null unique,
 	RFC varchar(15) not null,
 	calle varchar(20) not null,
	No int(5) not null,
 	colonia varchar(20) not null,
 	ciudad varchar(20) not null,
 	estado varchar(20) not null,
 	nombre_contacto varchar(70) not null,
 	telefono int(15) not null,
 	e_mail varchar(40) not null
 	);
 
create table ventas(
 	id_ventas int(4) PRIMARY KEY not null AUTO_INCREMENT
 	fecha datetime not null,
 	id_cliente int(4) not null,
 	subtotal double(7,2) not null,
 	IVA double not null,
 	total double not null,
 	FOREIGN KEY (id_cliente) REFERENCES clientes (id_cliente)
 	);
 
 create table productos(
	id_producto int (4) PRIMARY KEY not null AUTO_INCREMENT,
	producto varchar (30) not null,
	descripcion varchar (30) not null,
	precio_compra double not null,
	precio_venta double not null,
	existencias int(8)
	)ENGINE=InnoDB;
 
 create table detalle_ventas(
 	id_detalle_venta int(4) PRIMARY KEY not null AUTO_INCREMENT,
 	id_ventas int(4) not null,
 	id_producto int(4) not null,
 	cantidad int(5) not null,
 	tatal_producto int(7) not null,
 	precio double not null,
 	FOREIGN KEY (id_ventas) REFERENCES ventas (id_ventas),
 	FOREIGN KEY (id_producto) REFERENCES productos (id_producto)
 	)ENGINE=InnoDB;
 

 create table compras(
 	id_compra int(4) PRIMARY KEY not null AUTO_INCREMENT,
 	fecha datetime not null,
 	id_proveedor int(4) not null,
 	subtotal double not null,
 	IVA double not null,
 	total double not null,
 	FOREIGN KEY (id_proveedor) REFERENCES proveedores (id_proveedor)
 	)ENGINE=InnoDB;
 
 create table detalle_compras(
 	id_detalle_compra int(4) PRIMARY KEY not null AUTO_INCREMENT,
 	id_compra int(4) not null,
 	id_producto int(4) not null,
 	cantidad int(5) not null,
 	tatal_producto int(7) not null,
 	FOREIGN KEY (id_compra) REFERENCES compras (id_compra),
 	FOREIGN KEY (id_producto) REFERENCES productos (id_producto)
 	)ENGINE=InnoDB;

 create table usuarios(
	id_usuario int(3) primary key not null auto_increment,
	usuario varchar (20) not null,
	password varchar (20) not null,
	tipo varchar (20) not null,
	estado boolean
	)Engine=InnoDB;
	
create table login(
	id_login int(8) primary key not null auto_increment,
	usuario varchar(20) not null,
	nombre varchar(30) not null,
	apellido_pat varchar(15) not null,
	apellido_mat varchar(15) not null,
	password
	varchar(10) not null,
	tipo varchar(20) not null
	);

create table pedidos(
        id_pedido int(4) PRIMARY KEY not null AUTO_INCREMENT,
        producto int(4) not null,
        cantidad int(5) not null,
        fecha date not null,
        codigo int(4)not null,
        FOREIGN KEY (producto) REFERENCES productos (id_producto)
        );

create table pedidos_ingresados(
        id_pedido int(4) PRIMARY KEY not null AUTO_INCREMENT,
        producto int(4) not null,
        cantidad int(5) not null,
        fecha date not null,
        fecha_ingreso date not null,
        codigo int(4)not null,
        FOREIGN KEY (producto) REFERENCES productos (id_producto)
        );

insert into clientes values("1","Atahualpa","Flores","Rocha","7756475","mega.megaman@hotmail.com","chetos","Flores Magon","5","Centro","Centro","Hidlgo");
insert into proveedores values("1","Levis","GFJDK","Juarez","5","Centro","Santiago","Hidalgo","Gabriel","77564756","mega.megaman@hotmail.com");
insert into productos values ("1","Pantalon","Nuevo","40.2","100.0","10");
insert into pedidos values(1,1,1,now(),1000);
insert into login values ("1","confiur","Luis","Alarcon","Romero","12345","administrador");

