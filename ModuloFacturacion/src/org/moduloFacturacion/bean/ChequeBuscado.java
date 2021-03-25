package org.moduloFacturacion.bean;

import java.sql.Date;


public class ChequeBuscado {
    
    private int chequeNo;
    private String chequeLugarYFecha;
    private Double chequeMonto;
    private String chequePagoAlaOrdenDe ;

    public ChequeBuscado() {
    }

    public ChequeBuscado(int chequeNo, String chequeLugarYFecha, Double chequeMonto, String chequePagoAlaOrdenDe) {
        this.chequeNo = chequeNo;
        this.chequeLugarYFecha = chequeLugarYFecha;
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

    public String getChequeLugarYFecha() {
        return chequeLugarYFecha;
    }

    public void setChequeLugarYFecha(String chequeLugarYFecha) {
        this.chequeLugarYFecha = chequeLugarYFecha;
    }

    

    public Double getChequeMonto() {
        return chequeMonto;
    }

    public void setChequeMonto(Double chequeMonto) {
        this.chequeMonto = chequeMonto;
    }
 
    
    
}
