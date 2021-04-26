package org.moduloFacturacion.bean;

import java.sql.Date;


public class Cardex {
    
    private Date fechaCardex;
    private String noFacCardex;
    private String idTipoDesc;
    private Integer saldoCardex;
    private Integer totalCardex;
    private String productoDesc;
    
    public Cardex() {
    }

    public Cardex(Date fechaCardex, String noFacCardex, String idTipoDesc, Integer saldoCardex, Integer totalCardex, String productoDesc) {
        this.fechaCardex = fechaCardex;
        this.noFacCardex = noFacCardex;
        this.idTipoDesc = idTipoDesc;
        this.saldoCardex = saldoCardex;
        this.totalCardex = totalCardex;
        this.productoDesc = productoDesc;
    }

    public Date getFechaCardex() {
        return fechaCardex;
    }

    public void setFechaCardex(Date fechaCardex) {
        this.fechaCardex = fechaCardex;
    }

    public String getNoFacCardex() {
        return noFacCardex;
    }

    public void setNoFacCardex(String noFacCardex) {
        this.noFacCardex = noFacCardex;
    }

    public String getIdTipoDesc() {
        return idTipoDesc;
    }

    public void setIdTipoDesc(String idTipoDesc) {
        this.idTipoDesc = idTipoDesc;
    }

    public Integer getSaldoCardex() {
        return saldoCardex;
    }

    public void setSaldoCardex(Integer saldoCardex) {
        this.saldoCardex = saldoCardex;
    }

    public Integer getTotalCardex() {
        return totalCardex;
    }

    public void setTotalCardex(Integer totalCardex) {
        this.totalCardex = totalCardex;
    }

    public String getProductoDesc() {
        return productoDesc;
    }

    public void setProductoDesc(String productoDesc) {
        this.productoDesc = productoDesc;
    }
            
    
}
