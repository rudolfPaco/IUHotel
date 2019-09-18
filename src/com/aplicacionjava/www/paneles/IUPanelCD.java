/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.paneles;

import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author rudolf
 */
public class IUPanelCD extends JPanel{    
    private Color colorFuerte = new Color(242, 238, 236);
    private Color colorDebil = new Color(250, 250, 250 );
    private double arco = 1;
    private String tipoDegradado = "";
    
    public Limitacion limitacion;
    
    /**
     * panel Color Degradado con un color degradado predefinido, no se puede volverle opaco, por que tiene un color degradado ya definido.
     * @param limitacion objeto que se encarga de posicionar el componente panel dentro de el componente padre, dandole un tamaño fijo al panel.
     */
    public IUPanelCD(Limitacion limitacion){
        super(null);
        this.limitacion = limitacion;
        
        construirPanel();
    }
    private void construirPanel(){
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        setBorder(null); 
        setFocusable(false);
        repaint();
    }

    /**
     * cambia el color de fondo del degradado junto a un arco redondeado
     * @param colorFuerte color que predomina al panel en el degradado.
     * @param colorDebil color que debil que va mezclandose con el color fuerte.
     * @param tipoDegradado puede ser: HORIZONTAL, color fuerte desde arriba hacia abajo con el color debil. VERTICAL, color fuerte hacia la izquierda con el color debil hacia la derecha. y DIAGONAL, color fuerte desde la parte superior izquierda hasta la parte inferior derecha del color debil.
     * @param arco da un cierto redondeo al panel, dependiendo al numero de tipo double que se agrege.
     */
    public void setColorFondo(Color colorFuerte, Color colorDebil, String tipoDegradado, double arco){
        this.colorFuerte = colorFuerte;
        this.colorDebil = colorDebil;
        this.tipoDegradado = tipoDegradado;
        this.arco = arco;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        
        Shape figura_rectangulo = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arco, arco);
        
        g2.clip(figura_rectangulo);
        GradientPaint gradiente = null;
        
        switch(tipoDegradado){
            case "HORIZONTAL":
                gradiente = new GradientPaint(0, 0, colorFuerte, getWidth(), 0, colorDebil);
            break;
            case "VERTICAL":
                gradiente = new GradientPaint(0, 0, colorFuerte, 0, getHeight(), colorDebil);
            break;
            case "DIAGONAL":
                gradiente = new GradientPaint(0, 0, colorFuerte, getWidth()/2, getHeight()/2, colorDebil);
            break;
            default:
                gradiente = new GradientPaint(getWidth()/6, getHeight()/8, colorFuerte, getWidth(), getHeight(), colorDebil);
            break;
        }
        g2.setPaint(gradiente);
        g2.fill(figura_rectangulo);
    }

    public Color getColorFuerte() {
        return colorFuerte;
    }
    public void setColorFuerte(Color colorFuerte) {
        this.colorFuerte = colorFuerte;
        repaint();
    }
    public Color getColorDebil() {
        return colorDebil;
    }
    public void setColorDebil(Color colorDebil) {
        this.colorDebil = colorDebil;
        repaint();
    }
    public double getArco() {
        return arco;
    }
    public void setArco(double arco) {
        this.arco = arco;
        repaint();
    }
    public String getTipoDegradado() {
        return tipoDegradado;
    }
    public void setTipoDegradado(String tipoDegradado) {
        this.tipoDegradado = tipoDegradado;
        repaint();
    }
    public Limitacion getLimitacion() {
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion) {
        this.limitacion = limitacion;
        construirPanel();
    }
}
