package org.moduloFacturacion.bean;

import java.sql.Date;


public class Creditos {
    
    private Integer idCredito;
    private Date creaditoFechaInicio;
    private Date creditoFechaFinal;
    private Integer creditoDiasRestantes;
    private String creditoDesc;
    private String proveedorNombre;
    private Double creditoMonto;
    private String estadoCreditoDesc;

    public Creditos() {
    }

    public Creditos(Integer idCredito, Date creaditoFechaInicio, Date creditoFechaFinal, Integer creditoDiasRestantes, String creditoDesc, String proveedorNombre, Double creditoMonto, String estadoCreditoDesc) {
        this.idCredito = idCredito;
        this.creaditoFechaInicio = creaditoFechaInicio;
        this.creditoFechaFinal = creditoFechaFinal;
        this.creditoDiasRestantes = creditoDiasRestantes;
        this.creditoDesc = creditoDesc;
        this.proveedorNombre = proveedorNombre;
        this.creditoMonto = creditoMonto;
        this.estadoCreditoDesc = estadoCreditoDesc;
    }

    public Integer getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(Integer idCredito) {
        this.idCredito = idCredito;
    }
    
    public Integer getCreditoDiasRestantes() {
        return creditoDiasRestantes;
    }

    public void setCreditoDiasRestantes(Integer creditoDiasRestantes) {
        this.creditoDiasRestantes = creditoDiasRestantes;
    }

    public Date getCreaditoFechaInicio() {
        return creaditoFechaInicio;
    }

    public void setCreaditoFechaInicio(Date creaditoFechaInicio) {
        this.creaditoFechaInicio = creaditoFechaInicio;
    }

    public Date getCreditoFechaFinal() {
        return creditoFechaFinal;
    }

    public void setCreditoFechaFinal(Date creditoFechaFinal) {
        this.creditoFechaFinal = creditoFechaFinal;
    }

    public String getCreditoDesc() {
        return creditoDesc;
    }

    public void setCreditoDesc(String creditoDesc) {
        this.creditoDesc = creditoDesc;
    }

    public String getProveedorNombre() {
        return proveedorNombre;
    }

    public void setProveedorNombre(String proveedorNombre) {
        this.proveedorNombre = proveedorNombre;
    }

    public Double getCreditoMonto() {
        return creditoMonto;
    }

    public void setCreditoMonto(Double creditoMonto) {
        this.creditoMonto = creditoMonto;
    }

    public String getEstadoCreditoDesc() {
        return estadoCreditoDesc;
    }

    public void setEstadoCreditoDesc(String estadoCreditoDesc) {
        this.estadoCreditoDesc = estadoCreditoDesc;
    }
    
}
