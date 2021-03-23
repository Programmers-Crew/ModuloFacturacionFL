/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.moduloFacturacion.bean;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterJob;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.time.LocalDate;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

    

/**
 *
 * @author davis
 */
public class imprimirCheque implements Printable{
     PrinterJob printerJob;
    String chequeNo;
    String lugarYfecha,ordenDe,suma,valorTotal,desc;
    Image img= new Image("org/moduloFacturacion/img/comprobantecheque.png");
    BufferedImage image = SwingFXUtils.fromFXImage(img, null);
    LocalDate fechaActual = LocalDate.now();
    public imprimirCheque(){
        super();
        printerJob=PrinterJob.getPrinterJob();
        printerJob.setPrintable(this);       
    }
    // Método que lanza el cuadro de diálogos de la impresora e imprime
    // imagen
    public void imprima(String chequeNo,String lugarYfecha,String ordenDe, String suma, String valorTotal, String desc){
        this.chequeNo = chequeNo;
        this.lugarYfecha = lugarYfecha;
        this.ordenDe = ordenDe;
        this.suma = suma;
        this.valorTotal = valorTotal;
        this.desc = desc;
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
        
        g2d.drawImage(image, 25,25,400, 500,null);
        g2d.drawString(chequeNo,340,130);
        g2d.drawString(lugarYfecha,136, 148);
        g2d.drawString(ordenDe,130,175);
        g2d.drawString(valorTotal, 340, 175);
        g2d.drawString(String.valueOf(suma),130,200);     
        
         g2d.drawString(desc, 136, 290);
        g2d.drawString(String.valueOf(fechaActual), 136, 495);
         String[] arreglo = desc.split("\n");
         float x= 136;
         float y = 290;
         for (String arreglo1 : arreglo) {
             g2d.drawString(arreglo1, x, y);
             y=y+10;
         }
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
