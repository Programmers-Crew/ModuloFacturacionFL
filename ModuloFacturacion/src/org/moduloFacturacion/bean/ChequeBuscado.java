package org.moduloFacturacion.bean;

import java.sql.Date;


public class ChequeBuscado {
    
    private int chequeNo;
    private Date chequeFecha;
    private Double chequeMonto;
    private String chequePagoAlaOrdenDe ;

    public ChequeBuscado() {
    }

    public ChequeBuscado(int chequeNo, Date chequeFecha, Double chequeMonto, String chequePagoAlaOrdenDe) {
        this.chequeNo = chequeNo;
        this.chequeFecha = chequeFecha;
        this.chequeMonto = chequeMonto;
        this.chequePagoAlaOrdenDe = chequePagoAlaOrdenDe;
    }

    public String getChequePagoAlaOrdenDe() {
        return chequePagoAlaOrdenDe;
    }

    public void setChequePagoAlaOrdenDe(String chequePagoAlaOrdenDe) {
        this.chequePagoAlaOrdenDe = chequePagoAlaOrdenDe;
    }

    public int getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(int chequeNo) {
        this.chequeNo = chequeNo;
    }

    public Date getChequeFecha() {
        return chequeFecha;
    }

    public void setChequeFecha(Date chequeFecha) {
        this.chequeFecha = chequeFecha;
    }

    public Double getChequeMonto() {
        return chequeMonto;
    }

    public void setChequeMonto(Double chequeMonto) {
        this.chequeMonto = chequeMonto;
    }
 
    
    
}
