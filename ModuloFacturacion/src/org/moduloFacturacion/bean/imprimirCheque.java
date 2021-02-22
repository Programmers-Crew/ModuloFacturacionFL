/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.moduloFacturacion.bean;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterJob;
import java.time.LocalDate;
import javafx.collections.ObservableList;

/**
 *
 * @author davis
 */
public class imprimirCheque implements Printable{
     PrinterJob printerJob;
    ObservableList<Chequedetalle> listaChequeDetalle;   
    String chequeNo;
    String lugarYfecha,ordenDe,suma,valorTotal,recibi,nombre;
    String cantidad;
    LocalDate fechaActual = LocalDate.now();
    public imprimirCheque(){
        super();
        printerJob=PrinterJob.getPrinterJob();
        printerJob.setPrintable(this);       
    }
    // Método que lanza el cuadro de diálogos de la impresora e imprime
    // imagen
    public void imprima(ObservableList<Chequedetalle> listaChequeDetalle,String chequeNo,String lugarYfecha,String ordenDe, String suma, String valorTotal,String cantidad, String recibi, String nombre){
        this.listaChequeDetalle=listaChequeDetalle;
        this.chequeNo = chequeNo;
        this.lugarYfecha = lugarYfecha;
        this.ordenDe = ordenDe;
        this.suma = suma;
        this.valorTotal = valorTotal;
        this.cantidad = cantidad;
        this.recibi = recibi;
        this.nombre = nombre;
        if(printerJob.printDialog()){
            try{
                printerJob.print();
            }catch(Exception PrinterException){
                PrinterException.printStackTrace();
            }  
        }
    }
    // Método que traza la imagen para imprimir
    public void imprimir(Graphics2D g2d,PageFormat pf,int pagina){    
        Font font = new Font(null, Font.PLAIN, 0);    
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(-270),0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        g2d.setFont(rotatedFont);
        g2d.drawString(chequeNo,390,310);
        g2d.drawString(lugarYfecha,374, 90);
        g2d.drawString(ordenDe,350,72);
        g2d.drawString(cantidad,350,317);
        g2d.drawString(String.valueOf(fechaActual), 18, 105);
        g2d.drawString(recibi, 18, 5);
        g2d.drawString(nombre, 18, 205);
        g2d.drawString(String.valueOf(suma),325,74);
        int ancho =220;
        int largo = 5;
        int largoDesc = 105;
        int largoValor = 335;
        
        for(int x=0; x< listaChequeDetalle.size();x++){
            g2d.drawString(String.valueOf(listaChequeDetalle.get(x).getChequeDetalleCuenta()),ancho, largo);
            g2d.drawString(listaChequeDetalle.get(x).getChequeDetalleDesc(), ancho, largoDesc);
            g2d.drawString(String.valueOf(listaChequeDetalle.get(x).getChequeDetalleValor()), ancho, largoValor);
            ancho = ancho-17;
        }
         
         g2d.drawString(valorTotal, 20, 335);
    }
    
    
    
    public int print(Graphics g,PageFormat pf,int pagina){
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(pf.getImageableX(),pf.getImageableY());   
            switch(pagina){
                case 0:
                imprimir(g2d,pf,pagina);
                return (PAGE_EXISTS);            
                default:
                return (NO_SUCH_PAGE);
            }   
    }  
    
}
