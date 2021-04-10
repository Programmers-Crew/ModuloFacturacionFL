
package org.moduloFacturacion.bean;

import java.awt.Font;
import java.awt.print.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.text.DecimalFormat;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import org.moduloFacturacion.controller.MenuPrincipalContoller;

public class ImprimirFacA implements Printable{
    PrinterJob printerJob;
    ObservableList<FacturacionDetalleBackup> mensaje;   
    String Nit, nombreCliente, direccionCliente, totalLetras,totalFactura;
    LocalDate fecha;
    MenuPrincipalContoller menu = new MenuPrincipalContoller();
    
    float diax = Float.parseFloat(menu.facA.get("diax1", "root"));
    float diay = Float.parseFloat(menu.facA.get("diay1", "root"));
    
    float mesx = Float.parseFloat(menu.facA.get("mesx1", "root"));
    float mesy = Float.parseFloat(menu.facA.get("mesy1", "root"));
    
    float añox = Float.parseFloat(menu.facA.get("añox1", "root"));
    float añoy = Float.parseFloat(menu.facA.get("añoy1", "root"));
    
    float nombrex = Float.parseFloat(menu.facA.get("nombrex1", "root"));
    float nombrey = Float.parseFloat(menu.facA.get("nombrey1", "root"));
    
    float direccionx = Float.parseFloat(menu.facA.get("direccionx1", "root"));
    float direcciony = Float.parseFloat(menu.facA.get("direcciony1", "root"));
    
    float nitx = Float.parseFloat(menu.facA.get("nitx1", "root"));
    float nity = Float.parseFloat(menu.facA.get("nity1", "root"));
    
    float tablax = Float.parseFloat(menu.facA.get("tablax1", "root"));
    float tablay = Float.parseFloat(menu.facA.get("tablay1", "root"));
    
    float descfacx = Float.parseFloat(menu.facA.get("descfacx1", "root"));
    
    float valorx = Float.parseFloat(menu.facA.get("valorx1", "root"));
    
    float espaciado = Float.parseFloat(menu.facA.get("espaciado1", "root"));
    
    float totalfacx = Float.parseFloat(menu.facA.get("totalfacx1", "root"));
    float totalfacy = Float.parseFloat(menu.facA.get("totalfacy1", "root"));
    int tamaño = Integer.parseInt(menu.facA.get("tamaño1", "root"));

    
    public ImprimirFacA(){
        super();
        printerJob=PrinterJob.getPrinterJob();
        printerJob.setPrintable(this);       
    }
    
    public void imprima(ObservableList<FacturacionDetalleBackup> mensaje, String Nit, String nombreCliente, String direccionCliente,LocalDate fecha,String totalLetras, String totalFactura){
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
        g2d.drawString(String.valueOf(fecha.getDayOfMonth()), diay,diax);
        g2d.drawString(String.valueOf(fecha.getMonthValue()),mesy,mesx);
        g2d.drawString(String.valueOf(fecha.getYear()),añoy,añox);
        
            float ancho =tablay;
            float largo = tablax;
            float anchoDesc = descfacx;
            float anchoValor = valorx;
            float anchofor = ancho+espaciado;   
            DecimalFormat df = new DecimalFormat("###,###.00");
            System.out.println(mensaje.size());
        Font f1 = new Font(g2d.getFont().getFontName(),Font.PLAIN, tamaño);
        Font f1Rotated = f1.deriveFont(affineTransform);
        g2d.setFont(f1Rotated);
        g2d.drawString(direccionCliente,direcciony, direccionx); 
        
        g2d.drawString(Nit,nity , nitx);
        g2d.drawString(nombreCliente, nombrey, nombrex);
        for(int x=0; x< mensaje.size();x++){
        
            anchofor = anchofor-espaciado;
              String totalp = String.valueOf(df.format(mensaje.get(x).getTotalParcialBackup()));
//              String precio = String.valueOf(df.format(mensaje.get(x).getProductoPrecio()));
            g2d.drawString(String.valueOf(mensaje.get(x).getCantidadBackup()),anchofor, largo);
            g2d.drawString(mensaje.get(x).getProductoDesc(), anchofor , anchoDesc);
            g2d.drawString(String.valueOf(totalp), anchofor, anchoValor);

              
        }
        g2d.drawString(df.format(Double.parseDouble(totalFactura)), totalfacy, totalfacx);
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
