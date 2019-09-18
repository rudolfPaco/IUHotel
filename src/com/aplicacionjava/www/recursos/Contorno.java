/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.recursos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author Rudolf Felipez Mancilla
 */
public class Contorno extends AbstractBorder{
    protected Color colorFuerte;
    protected Color colorDebil;
    protected float grosorBorde;
    protected double arco;
    protected String TIPO_DEGRADADO;
    private boolean PRESIONO;
    
    /**
     * creando el borde redondeado, con un color gradiente.
     * @param color_fuerte primer color
     * @param color_debil segundo color
     * @param grosor_borde grosor del borde
     * @param arco que tan arqueado va ser el componente
     * @param TIPO_DEGRADADO de que lado a que lado van los colores, puede ser en forma: HORIZONTAL, VERTICAL o DIAGONAL
     */
    public Contorno(Color color_fuerte, Color color_debil, float grosor_borde, double arco, String TIPO_DEGRADADO){
        this.colorFuerte = color_fuerte;
        this.colorDebil = color_debil;
        this.grosorBorde = grosor_borde;
        this.arco = arco;
        this.TIPO_DEGRADADO = TIPO_DEGRADADO;
        this.PRESIONO = false;
    }
    @Override
    public void paintBorder(final Component c, Graphics g, int x, int y, int ancho, int alto){
        RoundRectangle2D rectangulo_redondo = crear_figura(x, y, ancho, alto);
        GradientPaint gradiente = crear_gradiente(c);
        BasicStroke borde = new BasicStroke(grosorBorde);
        
        Graphics2D grafico = (Graphics2D)g.create();
        grafico.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        grafico.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        
        grafico.setPaint(gradiente);
        grafico.setStroke(borde);
        grafico.drawRoundRect((int)rectangulo_redondo.getX(), (int)rectangulo_redondo.getY(), (int)rectangulo_redondo.getWidth(), (int)rectangulo_redondo.getHeight(), (int)rectangulo_redondo.getArcWidth(), (int)rectangulo_redondo.getArcHeight());
        c.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent me) {
                PRESIONO = true;
                c.repaint();                                
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                PRESIONO = false;
                c.repaint();
            }            
        });
    }
    private RoundRectangle2D crear_figura(int x, int y, int ancho, int alto){
        RoundRectangle2D rectangulo = new RoundRectangle2D.Double(x + grosorBorde/2, y + grosorBorde/2, ancho - grosorBorde, alto - grosorBorde, arco, arco);
        return rectangulo;
    }
    private GradientPaint crear_gradiente(Component componente){
        GradientPaint gradiente = null;
        if(PRESIONO)
            switch(TIPO_DEGRADADO){            
                case "HORIZONTAL":
                    gradiente = new GradientPaint(0, 0, colorDebil, componente.getWidth(), 0, colorFuerte);
                break;
                case "VERTICAL":
                    gradiente = new GradientPaint(0, 0, colorDebil, 0, componente.getHeight(), colorFuerte);
                break;
                case "DIAGONAL":
                    gradiente = new GradientPaint(0, 0, colorDebil, componente.getWidth()/2, componente.getHeight()/2, colorFuerte);
                break;
            }
        else
            switch(TIPO_DEGRADADO){
                case "HORIZONTAL":                
                    gradiente = new GradientPaint(0, 0, colorFuerte, componente.getWidth(), 0, colorDebil);
                break;
                case "VERTICAL":
                    gradiente = new GradientPaint(0, 0, colorFuerte, 0, componente.getHeight(), colorDebil);
                break;
                case "DIAGONAL":
                    gradiente = new GradientPaint(0, 0, colorFuerte, componente.getWidth()/2, componente.getHeight()/2, colorDebil);
                break;
            }
        return gradiente;
    }
    @Override
    public Insets getBorderInsets(Component c) {
        Insets insets = super.getBorderInsets(c);
        insets.left = insets.top = insets.right = insets.bottom += grosorBorde + grosorBorde/4 + 1;
        return insets;
    }
    public float getGrosorBorde(){
        return grosorBorde;
    }
    public Color getColorFuerteBorde(){
        return colorFuerte;
    }
    public Color getColorDebilBorde(){
        return colorDebil;
    }
    public double getArco(){
        return arco;
    }
}
