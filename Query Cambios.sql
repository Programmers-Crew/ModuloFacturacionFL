alter table Facturas change facturaId facturaId int(5) UNSIGNED ZEROFILL unique not null;

DELIMITER $$
	create procedure SpvalidarFactura(idFactura int(5))
		begin
			select * from Facturas
				where facturaId = idFactura;
        end $$ 
DELIMITER ;

call SpValidarFactura();