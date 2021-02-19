package org.moduloFacturacion.bean;

/**
 *
 * @author davis
 */
public class Chequedetalle {
    String chequeDetalleCuenta;
    String chequeDetalleDesc;
    double chequeDetalleValor;

    public Chequedetalle(String chequeDetalleCuenta, String chequeDetalleDesc, double chequeDetalleValor) {
        this.chequeDetalleCuenta = chequeDetalleCuenta;
        this.chequeDetalleDesc = chequeDetalleDesc;
        this.chequeDetalleValor = chequeDetalleValor;
    }

   

    public Chequedetalle() {
    }

    public String getChequeDetalleCuenta() {
        return chequeDetalleCuenta;
    }

    public void setChequeDetalleCuenta(String chequeDetalleCuenta) {
        this.chequeDetalleCuenta = chequeDetalleCuenta;
    }

    


    

    public String getChequeDetalleDesc() {
        return chequeDetalleDesc;
    }

    public void setChequeDetalleDesc(String chequeDetalleDesc) {
        this.chequeDetalleDesc = chequeDetalleDesc;
    }

    public double getChequeDetalleValor() {
        return chequeDetalleValor;
    }

    public void setChequeDetalleValor(double chequeDetalleValor) {
        this.chequeDetalleValor = chequeDetalleValor;
    }

    @Override
    public String toString() {
        return "Chequedetalle{" + "chequeDetalleCuenta=" + chequeDetalleCuenta + ", chequeDetalleDesc=" + chequeDetalleDesc + ", chequeDetalleValor=" + chequeDetalleValor + '}';
    }
    
}
