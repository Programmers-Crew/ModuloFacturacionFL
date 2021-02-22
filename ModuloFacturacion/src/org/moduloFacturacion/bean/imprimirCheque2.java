/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.moduloFacturacion.bean;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterJob;

/**
 *
 * @author davis
 */
public class imprimirCheque2 implements Printable{
     PrinterJob printerJob;
    String lugarYfecha,ordenDe,suma;
    double cantidad;
    public imprimirCheque2(){
        super();
        printerJob=PrinterJob.getPrinterJob();
        printerJob.setPrintable(this);       
    }
    // Método que lanza el cuadro de diálogos de la impresora e imprime
    // imagen
    public void imprima(String lugarYfecha,double cantidad, String ordenDe, String suma, String valorTotal){
        this.lugarYfecha = lugarYfecha;
        this.cantidad = cantidad;
        this.ordenDe = ordenDe;
        this.suma = suma;
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
        pf.setOrientation(PageFormat.LANDSCAPE);
       g2d.drawString(lugarYfecha, 15,15);
    //g2d.drawString(ordenDe,70, 120);
    //g2d.drawString(String.valueOf(suma),410 , 120);
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
