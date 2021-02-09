create database ModuloFacturacionFL;
use ModuloFacturacionFL;

create table EstadoProductos(
	estadoProductoId tinyint(1) primary key auto_increment,
    estadoProductoDesc varchar(20) unique not null
);

create table Proveedores(
	proveedorId int(5) UNSIGNED ZEROFILL primary key,
    proveedorNombre varchar(30) unique not null,
	proveedorTelefono varchar(8) unique not null
);

create table CategoriaProductos(
	categoriaId int(5) UNSIGNED ZEROFILL primary key,
    categoriaNombre varchar(30) unique not null
);

create table Productos(
	productoId	int(5) UNSIGNED ZEROFILL primary key,
    productoDesc varchar(30) not null,
	proveedorId int(5) UNSIGNED ZEROFILL not null,
    categoriaId int(5) UNSIGNED ZEROFILL not null,
    productoPrecio decimal(10,2) not null,

    CONSTRAINT FK_ProveedorProductos FOREIGN KEY (proveedorId) REFERENCES Proveedores(proveedorId),
    CONSTRAINT FK_CategoriaProductos FOREIGN KEY (categoriaId) REFERENCES CategoriaProductos(categoriaId)
);

create table InventarioProductos(
	inventarioProductoId int(5) UNSIGNED ZEROFILL primary key auto_increment,
    inventarioProductoCant int(5)  not null,
    productoId int(5) UNSIGNED ZEROFILL not null unique,
    estadoProductoId tinyint(1) not null,
    
	CONSTRAINT FK_ProductoInventario FOREIGN KEY (productoId) REFERENCES Productos(productoId),
	CONSTRAINT FK_EstadoProductoInventario FOREIGN KEY (estadoProductoId) REFERENCES EstadoProductos(estadoProductoId)
);


