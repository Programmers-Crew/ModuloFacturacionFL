alter table Facturas change facturaId facturaId int(5) UNSIGNED ZEROFILL unique not null;

DELIMITER $$
	create procedure SpvalidarFactura(idFactura int(5))
		begin
			select * from Facturas
				where facturaId = idFactura;
        end $$ 
DELIMITER ;

DELIMITER $$
	create procedure SpUpdateClientes(nit varchar(9) ,nombre varchar(25), direccion varchar(100))
		begin
			update clientes
				set 
                    clienteNombre = nombre,
                    clienteDireccion = direccion
			where 
				clienteNit = nit;
        end $$
DELIMITER ;

DELIMITER $$
	create procedure SpBuscarCliente(nit varchar(9))
		begin
			select * from clientes
			where
             (clienteNit = nit);
		end $$
DELIMITER ;

DELIMITER $$
	create procedure SpBuscarCF(nit varchar(9))
		begin
			select * from clientes
			where
             (clienteNit = nit);
		end $$
DELIMITER ;

DELIMITER $$
	create procedure SpUpdateCF(nit varchar(9))
		begin
			update clientes
				set 
                    clienteNombre = "C/F",
                    clienteDireccion = "Ciudad de Guatemala"
			where 
				clienteNit = nit;
        end $$
DELIMITER ;



## Cambios 30-03-2021

DELIMITER $$
	create procedure SpValidarProducto(idbuscado varchar(8))
		begin 
			select * from productos
				where  productoId= idBuscado;
        end $$
DELIMITER ;


DELIMITER $$
	create procedure SpValidarProveedor(idbuscado varchar(8))
		begin 
			select * from proveedores
				where  proveedorId= idBuscado;
        end $$
DELIMITER ;

DELIMITER $$
	create procedure SpBuscarProductoProv(nombre varchar(50))
		BEGIN
			select 
				p.proveedorId,
                p.proveedorNombre, 
                pr.productoDesc
			from 
				Proveedores as p
			inner join
				productos as pr
			on pr.proveedorId = p.proveedorId
			where proveedorNombre = nombre
			order by proveedorId asc;
        END $$
DELIMITER ;

DELIMITER $$
	create procedure SpBuscarPrecioProd(nombre varchar(40))
		begin
			select 
				productoPrecio
			from 
				productos
			where productoDesc = nombre;
        end $$
DELIMITER ;

DELIMITER $$
	create procedure SpBucarCreditosVencidos()
		begin 
			select idCredito
				from creditos
					where creditoDiasRestantes < 0;
        end $$
DELIMITER ;

