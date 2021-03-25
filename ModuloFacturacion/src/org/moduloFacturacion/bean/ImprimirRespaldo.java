
package org.moduloFacturacion.bean;

import java.awt.Font;
import java.awt.print.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.text.DecimalFormat;
import java.time.LocalDate;
import javafx.collections.ObservableList;

public class ImprimirRespaldo implements Printable{
    PrinterJob printerJob;
    ObservableList<ProductoBuscado> mensaje;   
    String Nit, nombreCliente, direccionCliente,totalFactura;
    LocalDate fecha;
    public ImprimirRespaldo(){
        super();
        printerJob=PrinterJob.getPrinterJob();
        printerJob.setPrintable(this);       
    }
    
    public void imprima(ObservableList<ProductoBuscado> mensaje, String Nit, String nombreCliente, String direccionCliente,LocalDate fecha, String totalFactura){
    this.mensaje=mensaje;
    this.Nit = Nit;
    this.nombreCliente = nombreCliente;
    this.direccionCliente = direccionCliente;
    this.fecha = fecha;
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
        g2d.drawString(String.valueOf(fecha.getDayOfMonth()), 395,28);
        g2d.drawString(String.valueOf(fecha.getMonthValue()),395,68);
        g2d.drawString(String.valueOf(fecha.getYear()),395,110);
        g2d.drawString(nombreCliente, 370, 70);
        g2d.drawString(direccionCliente,355, 70);
        g2d.drawString(Nit,355 , 218);
            int ancho =320;
            int largo = 25;
            int anchoDesc = 70;
            
        DecimalFormat df = new DecimalFormat("#.00");
            int anchoValor = 227;
          for(int x=0; x< mensaje.size();x++){
               String precio = String.valueOf(df.format(mensaje.get(x).getProductoPrecio()));
               String totalp = String.valueOf(df.format(mensaje.get(x).getCantidad()*mensaje.get(x).getProductoPrecio()));
              g2d.drawString(String.valueOf(mensaje.get(x).getCantidad()),ancho, largo);
              g2d.drawString(mensaje.get(x).getProductoDesc()+"  "+String.valueOf(precio), ancho , anchoDesc);
              g2d.drawString(String.valueOf(totalp), ancho, anchoValor);
              
              ancho = ancho-18;
          }
          System.out.println(totalFactura+"hola");
         g2d.drawString(totalFactura, 50, 232);
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
