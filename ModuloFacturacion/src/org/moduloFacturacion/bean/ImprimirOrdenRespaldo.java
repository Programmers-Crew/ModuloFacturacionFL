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
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.time.LocalDate;
import javafx.collections.ObservableList;

/**
 *
 * @author davis
 */
public class ImprimirOrdenRespaldo implements Printable{
     PrinterJob printerJob;
    ObservableList<ProductoBuscado> mensaje;   
    String Nit, nombreCliente, direccionCliente, totalLetras,totalFactura;
    LocalDate fecha;
    public ImprimirOrdenRespaldo(){
        super();
        printerJob=PrinterJob.getPrinterJob();
        printerJob.setPrintable(this);       
    }
    
    public void imprima(ObservableList<ProductoBuscado> mensaje, String Nit, String nombreCliente, String direccionCliente,LocalDate fecha,String totalLetras, String totalFactura){
    this.mensaje=mensaje;
    this.Nit = Nit;
    this.nombreCliente = nombreCliente;
    this.direccionCliente = direccionCliente;
    this.fecha = fecha;
    this.totalLetras = totalLetras;
    this.totalFactura = totalFactura;
     if(printerJob.printDialog()){
        try{
            printerJob.print();
        }catch(Exception PrinterException){
            PrinterException.printStackTrace();
        }        
        } 
    }
    public void imprimir(Graphics2D g2d,PageFormat pf,int pagina){   
        Font font = new Font(null, Font.PLAIN, 0);    
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(-270),0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        g2d.setFont(rotatedFont);
        g2d.drawString(String.valueOf(fecha.getDayOfMonth()), 320,28);
        g2d.drawString(String.valueOf(fecha.getMonthValue()),320,68);
        g2d.drawString(String.valueOf(fecha.getYear()),320,110);
        g2d.drawString(nombreCliente, 297, 68);
        g2d.drawString(direccionCliente,279, 68);
//        g2d.drawString(Nit,362 , 215);
            int ancho =242;
            int largo = 28;
            int anchoDesc = 68;
            
        DecimalFormat df = new DecimalFormat("#.00");
            int anchoValor = 237;
          for(int x=0; x< mensaje.size();x++){
              String totalp = String.valueOf(df.format(mensaje.get(x).getCantidad()*mensaje.get(x).getProductoPrecio()));
              String precio = String.valueOf(df.format(mensaje.get(x).getProductoPrecio()));
              g2d.drawString(String.valueOf(mensaje.get(x).getCantidad()),ancho, largo);
              g2d.drawString(mensaje.get(x).getProductoDesc()+"  "+String.valueOf(precio), ancho , anchoDesc);
              g2d.drawString(String.valueOf(totalp), ancho, anchoValor);
              
              ancho = ancho-18;
          }
         g2d.drawString(totalFactura, 40, 240);
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
