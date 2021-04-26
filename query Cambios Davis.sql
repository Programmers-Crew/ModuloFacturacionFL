-- procreso de actualizar inventario desde factura - 8/04/2021
DELIMITER $$
	create procedure SpActualizarInventarioProductosFacturacion(idBuscado varchar(7), cant int(100),estado tinyint(1))
		BEGIN
			update InventarioProductos
				set  estadoProductoId = estado, inventarioproductos.inventarioProductoCant = cant
					where productoId = idBuscado;
        END $$
DELIMITER ;


DELIMITER $$ 
	CREATE PROCEDURE SpListarCredit()
		BEGIN
			SELECT *
            FROM creditos
            WHERE creditoEstado = 1;
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpMarcarPagadocFac(idBuscado int)
		begin
			update Creditos as c
				set creditoEstado = 2 
					where c.noFactura = idBuscado;
        end $$
DELIMITER ;

DELIMITER $$
	create procedure SpBuscarProductosFac(idBuscado varchar(7))
		BEGIN
			select 
				pr.productoId,
                pr.productoDesc,
                p.proveedorNombre,
                p.proveedorId,
                cp.categoriaNombre,
                pr.precioCosto,
                pr.productoPrecio,
                tp.tipoProdDesc,
                ip.inventarioProductoCant
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
			inner join 
				tipoProducto as tp
			on 
				pr.tipoProductoId = tp.tipoProdId
			inner join
				inventarioproductos as ip
			on pr.productoId = ip.productoId
            
			where pr.productoId = idBuscado and ip.estadoProductoId = 1
			order by pr.productoId ASC;
        END $$
DELIMITER ;


DELIMITER $$
	create procedure SpListarProductosFac()
		BEGIN
			select 
				pr.productoId,
                pr.productoDesc,
                p.proveedorNombre,
                cp.categoriaNombre,
                pr.precioCosto,
                pr.productoPrecio,
                tp.tipoProdDesc,
                ip.inventarioProductoCant
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
			inner join 
				tipoProducto as tp
			on 
				pr.tipoProductoId = tp.tipoProdId
			inner join
				inventarioproductos as ip
			on pr.productoId = ip.productoId
            where ip.inventarioProductoCant > 0
			order by
				pr.productoId ASC;
        END $$
DELIMITER ;
        