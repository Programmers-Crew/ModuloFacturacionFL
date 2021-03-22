
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

/**
 *
 * @author davis
 */
public class imprimirCheque2 implements Printable{
     PrinterJob printerJob;
    String lugarYfecha,ordenDe,suma,valorTotal;
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
        this.valorTotal = valorTotal;
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
         Font font = new Font(null, Font.PLAIN, 0);    
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(-270),0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        g2d.setFont(rotatedFont);
        g2d.drawString(lugarYfecha, 115,55);
        g2d.drawString(ordenDe,97, 64);
        g2d.drawString(String.valueOf(suma),79,50);
        g2d.drawString(valorTotal,115,378);
        
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
