package org.moduloFacturacion.bean;

import java.sql.Date;


public class Cardex {
    
    private Date fechaCardex;
    private String noFacCardex;
    private String idTipoDesc;
    private Integer saldoCardex;
    private Integer entradaCardex;
    private Integer totalCardex;
    private String productoDesc;
    private String DescTipoDocumento;
    
    public Cardex() {
    }

    public Cardex(Date fechaCardex, String noFacCardex, String idTipoDesc, Integer saldoCardex, Integer entradaCardex, Integer totalCardex, String productoDesc, String DescTipoDocumento) {
        this.fechaCardex = fechaCardex;
        this.noFacCardex = noFacCardex;
        this.idTipoDesc = idTipoDesc;
        this.saldoCardex = saldoCardex;
        this.entradaCardex = entradaCardex;
        this.totalCardex = totalCardex;
        this.productoDesc = productoDesc;
        this.DescTipoDocumento = DescTipoDocumento;
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

    public String getDescTipoDocumento() {
        return DescTipoDocumento;
    }

    public void setDescTipoDocumento(String DescTipoDocumento) {
        this.DescTipoDocumento = DescTipoDocumento;
    }

    public Integer getEntradaCardex() {
        return entradaCardex;
    }

    public void setEntradaCardex(Integer entradaCardex) {
        this.entradaCardex = entradaCardex;
    }
            
    
    
}
