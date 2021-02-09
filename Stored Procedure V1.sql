-- PROCEDURE V1

#-------------- Entidad Proveedores 
DELIMITER $$
	create procedure SpListarProveedores()
		BEGIN
			select
				proveedorId,
                proveedorNombre,
                proveedorTelefono
			from 
				Proveedores
			order by
				proveedorId asc;
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpAgregarProveedores(proveedores int(100),nombre varchar(50), telefono varchar(8))
		BEGIN
			insert into Proveedores(proveedorId,proveedorNombre, proveedorTelefono)
				values (proveedores , nombre , telefono);
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpActualizarProveedor(idBuscado int(100), nuevoCodigo int(100),nuevoNombre varchar(50), nuevoTelefono varchar(8))
		BEGIN
			update 
				Proveedores
			set
				proveedorId = nuevoCodigo,
				proveedorNombre = nuevoNombre,
                proveedorTelefono = nuevoTelefono
			where
				proveedorId = idBuscado;
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpBuscarProveedoresPorId(idBuscado int(100))
		BEGIN
			select 
				proveedorId,
                proveedorNombre,
                proveedorTelefono
			from
				Proveedores 
			where
				proveedorId = idBuscado
			order by
				proveedorId asc;
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpBuscarProveedoresPorNombre(nombre varchar(30))
		BEGIN
			select 
				proveedorId,
				proveedorNombre,
                proveedorTelefono
			from
				Proveedores 
			where
				proveedorNombre = nombre
			order by
				proveedorId asc;
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpEliminarProveedor(idBuscado int(100))
		BEGIN
			delete from 
				Proveedores
			where 
				proveedorId = idBuscado;
        END $$
DELIMITER ;



#------------------ Entidad Categoria Productos
DELIMITER $$
	create procedure SpListarCategoriaProductos()
		BEGIN
			select
				categoriaId,
                categoriaNombre
			from
				CategoriaProductos
			order by 
				categoriaId asc;
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpAgregarCategoriaProductos(id int(100), nombre varchar(50))
		BEGIN
			insert into CategoriaProductos(categoriaId, categoriaNombre)
				values(id, nombre);
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpActualizarCategoriaProductos(idBuscado int(100),idNuevo int(100), nuevoNombre varchar(50))
		BEGIN
			update 
				CategoriaProductos
			set
				categoriaId =idNuevo,
                categoriaNombre = nuevoNombre
			where
				categoriaId = idBuscado;
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpBuscarCategoriaProductos(idBuscado int(100))
		BEGIN
			select 
				categoriaNombre,
				categoriaId
            from
				CategoriaProductos
			where
				categoriaId = idBuscado
			order by
				categoriaId asc;
		END $$
DELIMITER ;


DELIMITER $$
	create procedure SpEliminarCategoriaProductos(idBuscado int(100))
		BEGIN
			delete from 
				CategoriaProductos
			where
				categoriaId = idBuscado;
        END $$
DELIMITER ;



#-------------------------- Entidad de productos
DELIMITER $$
	create procedure SpListarProductos()
		BEGIN
			select
				pr.productoId,
                pr.productoDesc, 
                p.proveedorNombre,
                cp.categoriaNombre,
                pr.productoPrecio
			from 
				Productos as pr
			inner join
				Proveedores as p
			on
				pr.proveedorId = p.proveedorId
			inner join
				CategoriaProductos as cp
			on
				pr.categoriaId = cp.categoriaId 
			order by
				pr.productoId ASC;
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpAgregarProductos(id int(100), descr varchar(50), provId int(100), catId int(100), precio decimal(10,2))
		BEGIN
			insert into Productos(productoId, productoDesc, proveedorId, categoriaId, productoPrecio)
				values(id, descr, provId, catId, precio);
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpActualizarProductos(idBuscado int(100),id int(100), descr varchar(50), precio decimal(10,2))
		BEGIN
			update 
				Productos
			set
				productoId = id,
                productoDesc = descr,
                productoPrecio = precio
			where
				productoId = idBuscado;
		END $$
DELIMITER ;


DELIMITER $$
	create procedure SpBuscarProductos(idBuscado int(100))
		BEGIN
			select 
				pr.productoId,
                pr.productoDesc,
                p.proveedorNombre,
                cp.categoriaNombre,
                pr.productoPrecio
			from
				Productos as pr
			inner join
				Proveedores as p
			on 
				pr.proveedorId = p.proveedorId
			inner join
				CategoriaProductos as cp
			on
				pr.categoriaId = cp.categoriaId
			where
				pr.productoId = idBuscado
			order by
				pr.productoId ASC;
        END $$
DELIMITER ;


DELIMITER $$
create procedure SpBuscarProductosNombre(nombreProductos varchar(50))
		BEGIN
			select
				pr.productoId,
                pr.productoDesc,
                p.proveedorNombre,
                cp.categoriaNombre,
                pr.productoPrecio
			from
				Productos as pr
			inner join 
				Proveedores as p
			on 
				pr.proveedorId = p.proveedorId
			inner join
				CategoriaProductos as cp
			on
				pr.categoriaId = cp.categoriaId
			where
				pr.productoDesc = nombreProductos
			order by
				pr.productoId ASC;
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpEliminarProductos(idBuscado int(100))
		BEGIN
			delete from
				Productos 
			where
				productoId = idBuscado;
		END $$
DELIMITER ;


DELIMITER $$
	CREATE PROCEDURE spVerificarCategoria(categoria varchar(50))
		BEGIN
			select * from
				categoriaProductos as cp
			where
				cp.categoriaNombre = categoria;
        END $$
        
DELIMITER ;


DELIMITER $$
	CREATE PROCEDURE spVerificarProveedores(proveedor varchar(50))
		BEGIN
			select * from
				proveedores as p 
			where 
				p.proveedorNombre = proveedor;
        END $$
        
DELIMITER ;


#----------------- Entidad Inventario de productos
DELIMITER $$
	create procedure SpListarInventarioProductos()
		BEGIN
			select
				p.productoId, 
				ip.inventarioProductoCant, 
                pr.proveedorNombre,
                p.productoDesc, 
                ep.estadoProductoDesc
			from
				InventarioProductos as ip
			inner join
				Productos as p
			on
				ip.productoId = p.productoId
			inner join
				EstadoProductos as ep
			on
				ip.estadoProductoId = ep.estadoProductoId
			inner join
				Proveedores as pr
			on
				p.proveedorId = pr.proveedorId
			order by
				p.productoId ASC;
        END $$
DELIMITER ;


DELIMITER $$	
	create procedure SpAgregarInventarioProductos(cant int(100), producto int(100), estado tinyint(1))
		BEGIN
			insert into InventarioProductos(inventarioProductoCant, productoId, estadoProductoId)
				values(cant, producto, estado);
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpActualizarInventarioProductos(idBuscado int(100), cant int(100),estado tinyint(1))
		BEGIN
			update 
				InventarioProductos
			set
				inventarioProductoCant = cant,
                estadoProductoId = estado
			where
				productoId = idBuscado;
        END $$
DELIMITER ;



DELIMITER $$
	create procedure SpBuscarInventarioProductos(idBuscado int(100))
		BEGIN
			select
				p.productoId,
				ip.inventarioProductoCant, 
				pr.proveedorNombre, 
				p.productoDesc, 
				ep.estadoProductoDesc
			from 
                InventarioProductos as ip
			inner join 
				Productos as p
			on 
				ip.productoId = p.productoId
			inner join
				EstadoProductos as ep
			on 
				ip.estadoProductoId = ep.estadoProductoId
			inner join
                 Proveedores as pr
			on
                p.proveedorId = pr.proveedorId
			where 
				p.productoId = idBuscado
			order by
                p.productoId ASC;
        END $$
DELIMITER ;



DELIMITER $$
	create procedure SpEliminarInventarioProductos(idBuscado int(100))
		BEGIN
			delete from 
				InventarioProductos
			where 
                productoId = idBuscado;
        END $$
DELIMITER ;

-- inserts obligatorios
insert into estadoProductos(estadoProductoDesc) values("EXITENCIA"), ("AGOTADO")