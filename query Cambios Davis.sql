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
        