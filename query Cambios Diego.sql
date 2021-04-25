DELIMITER $$
	create procedure SpCostoPromedio(idBuscado varchar(50), precioNuevo double)
		begin
			update Productos as p
				set p.costoAntiguo = p.precioCosto,
					p.precioCosto = (p.costoAntiguo + precioNuevo)/2
				where productoId = idBuscado;
        end $$
DELIMITER ;

DELIMITER $$
	create procedure SpCostoPromedioSinCantidad(idBuscado varchar(50), precioNuevo double)
		begin
			update Productos as p
				set p.costoAntiguo = p.precioCosto,
					p.precioCosto =  precioNuevo
				where productoId = idBuscado;
        end $$
DELIMITER ;

# QUERYS PARA REALIZAR EL CARDEX
DELIMITER $$
create procedure SpListarCardex(nombreProducto varchar(50))
	begin 
		select 	f.facturaFecha,f.facturaId, f.facturaSerie,
				p.productoDesc as Salida, fd.cantidad as Total, (fd.cantidad-i.inventarioProductoCant) as SaldoSalida,
				c.creaditoFechaInicio ,c.noFactura,
				p.productoDesc as Entrada, cd.cantidadDetalle Total, (cd.cantidadDetalle + i.inventarioProductoCant) as SaldoEntrada
        From 
			facturas as f, creditos as c
				inner join facturadetalle  as fd
                on f.facturaDetalleId = fd.facturaDetalleId
			inner join creditodetalle as cd
				on c.creditoDetalle = cd.idCreditoDetalle
			inner join productos as p
				on fd.productoId = p.productoId or cd.productoId = p.productoId
			inner join inventarioproductos as i
				on p.productoId = i.inventarioProductoId
			where p.productoDesc = nombreProducto
        ;
    end $$
DELIMITER ;


