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
call SpListarCardex('HP00002')


DELIMITER $$
	create procedure SpGenerarCardex(prodId varchar(7))
		begin 
        end $$
DELIMITER ;

DELIMITER $$
	create procedure SpAgregarCardexFac(fecha date, nombre varchar(60),NoFac int, tipo int, cantidad int)
		begin
			insert into cardex (fechaCardex,noFacCardex,tipoCardex,saldoCardex, totalCardex, producto)
				select fecha, noFac, tipo,cantidad,ip.inventarioProductoCant, p.productoId
					from inventarioproductos as ip
						inner join productos as p
							on ip.productoId = p.productoId
						where p.productoDesc = nombre;
        end $$
DELIMITER ;
 

DELIMITER $$
	create procedure SpAgregarCardexFacUpdate(nombre varchar(60),tipo int, cantidad int, idBuscado int)
		begin
			insert into cardex (fechaCardex,noFacCardex,tipoCardex,saldoCardex, totalCardex, producto )
				select creditos.creaditoFechaInicio, creditos.noFactura, tipo,cantidad,inventarioproductos.inventarioProductoCant, p.productoId
					from inventarioproductos
						inner join productos as p					
							on inventarioproductos.productoId = p.productoId,
					Creditos
						where (p.productoDesc = nombre) and (creditos.noFactura = idBuscado);
        end $$
DELIMITER ;
