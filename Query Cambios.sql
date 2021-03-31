alter table Facturas change facturaId facturaId int(5) UNSIGNED ZEROFILL unique not null;

DELIMITER $$
	create procedure SpvalidarFactura(idFactura int(5))
		begin
			select * from Facturas
				where facturaId = idFactura;
        end $$ 
DELIMITER ;

call SpValidarFactura();

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

call SpBuscarCliente("554499-9","Diego Gonzalez","San miguel Petapa");