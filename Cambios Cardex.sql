alter table Cardex add entradaCardex int;

drop procedure SpGenerarCardexProd;
drop procedure SpGenerarCardex;
drop procedure SpGenerarCardexProd;
drop procedure SpGenerarCardexFechaProd;
drop procedure SpGenerarCardexFecha;

SET SQL_SAFE_UPDATES = 0;

DELIMITER $$
	create procedure SpGenerarCardexProd(prodId varchar(30))
		begin 
			SELECT DISTINCT  c.fechaCardex, c.noFacCardex,tc.idTipoDesc ,c.saldoCardex, c.entradaCardex,c.totalCardex, p.productoDesc, td.DescTipoDocumento, pr.proveedorNombre
			from cardex as c
			inner join tipocardex as tc
				on tc.idTipoCardex = c.tipoCardex
			inner join productos as p
				on p.productoId = producto
			inner join proveedores as pr
				on pr.proveedorId = p.proveedorId
			inner join tipodocumento as td
				on td.idTipoDocumento = c.tipoDocumento
			WHERE p.productoId = prodId or p.productoDesc = prodId
            order by c.idCardex desc
;
        end $$
DELIMITER ;

DELIMITER $$
	create procedure SpBuscarProveedorCardex(prodId varchar(30))
		begin 
			select pr.proveedorNombre
			from productos as p
				inner join proveedores as pr
					on pr.proveedorId = p.proveedorId
						where (pr.proveedorId = prodId) or (pr.proveedorNombre = prodId);
		end $$
DELIMITER ;


DELIMITER $$
	create procedure SpAgregarCardexCreditos(fecha date, nombre varchar(60),NoFac int, tipo int, cantidad int, documento int(5))
		begin
			insert into cardex (fechaCardex,noFacCardex,tipoCardex,entradaCardex, totalCardex, producto, tipoDocumento)
				select fecha, noFac, tipo,cantidad,ip.inventarioProductoCant, p.productoId, documento
					from inventarioproductos as ip
						inner join productos as p
							on ip.productoId = p.productoId
						where p.productoDesc = nombre;
        end $$
DELIMITER ;

DELIMITER $$
	create procedure SpAgregarCardexCreUpdate(nombre varchar(60),tipo int, cantidad int, idBuscado int, documento int(5))
		begin
			insert into cardex (fechaCardex,noFacCardex,tipoCardex,entradaCardex, totalCardex, producto,tipoDocumento )
				select creditos.creaditoFechaInicio, creditos.noFactura, tipo,cantidad,inventarioproductos.inventarioProductoCant, p.productoId,documento
					from inventarioproductos
						inner join productos as p					
							on inventarioproductos.productoId = p.productoId,
					Creditos
						where (p.productoDesc = nombre) and (creditos.noFactura = idBuscado);
        end $$
DELIMITER ;

#
DELIMITER $$
	create procedure SpGenerarCardexFecha(prodId varchar(30), inicio date, final date)
		begin 
			SELECT DISTINCT  c.fechaCardex, c.noFacCardex,tc.idTipoDesc ,c.saldoCardex, c.entradaCardex,c.totalCardex, p.productoDesc, td.DescTipoDocumento
			from cardex as c
			inner join tipocardex as tc
				on tc.idTipoCardex = c.tipoCardex
			inner join productos as p
				on p.productoId = producto
			inner join tipodocumento as td
				on td.idTipoDocumento = c.tipoDocumento
			WHERE p.productoId = prodId and c.fechaCardex BETWEEN inicio AND final
                        order by c.idCardex desc
;
        end $$
DELIMITER ;

DELIMITER $$
	create procedure SpGenerarCardexFechaProd(prodId varchar(30), inicio date, finalFecha date)
		begin 
			SELECT DISTINCT  c.fechaCardex, c.noFacCardex,tc.idTipoDesc ,c.saldoCardex, c.entradaCardex,c.totalCardex, p.productoDesc, td.DescTipoDocumento
			from cardex as c
			inner join tipocardex as tc
				on tc.idTipoCardex = c.tipoCardex
			inner join productos as p
				on p.productoId = producto
			inner join tipodocumento as td
				on td.idTipoDocumento = c.tipoDocumento
			WHERE p.productoId = prodId or p.productoDesc = prodId and c.fechaCardex BETWEEN inicio AND finalFecha
            order by c.idCardex desc;
        end $$
DELIMITER ;

DELIMITER $$
	create procedure SpGenerarCardexProd(prodId varchar(30))
		begin 
			SELECT DISTINCT  c.fechaCardex, c.noFacCardex,tc.idTipoDesc ,c.saldoCardex, c.entradaCardex,c.totalCardex, p.productoDesc, td.DescTipoDocumento, pr.proveedorNombre
			from cardex as c
			inner join tipocardex as tc
				on tc.idTipoCardex = c.tipoCardex
			inner join productos as p
				on p.productoId = producto
			inner join proveedores as pr
				on pr.proveedorId = p.proveedorId
			inner join tipodocumento as td
				on td.idTipoDocumento = c.tipoDocumento
			WHERE p.productoId = prodId or p.productoDesc = prodId
            order by c.idCardex desc
;
        end $$
DELIMITER ;