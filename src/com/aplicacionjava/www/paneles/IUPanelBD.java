/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.paneles;

import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author rudolf
 */
public class IUPanelBD extends JPanel{
    
    private boolean estaEncimaBoton;
    private boolean estadoEvento;
    
    protected Limitacion limitacion;    
    private int arco;
    private Color colorFondo = new Color(242, 238, 236);
    private Color colorSuave = new Color(250, 250, 250 );
    private Color colorBorde = new Color(170, 170, 170);
    
    /**
     * panel Borde Degradado con un color de fondo degradado predefinido y un borde redondeado. No se puede volverle opaco, por que tiene un color degradado ya definido.
     * @param limitacion objeto que se encarga de posicionar el componente panel dentro de el componente padre, dandole un tamaño fijo al panel.
     */
    public IUPanelBD(Limitacion limitacion){
        super(null);
        this.limitacion = limitacion;
        
        construirPanel();
        escucharEventos();
    }
    private void construirPanel(){
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        setArco(limitacion.getPorcentajeAlto(4) + limitacion.getPorcentajeAncho(4));
        setEstadoEvento(false);
        setFocusable(false);
        setOpaque(false);
        setBorder(null);
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gradiente = new GradientPaint( 0, 0, colorSuave, 0, getHeight(), colorFondo );
        
        if ( estaEncimaBoton )
            gradiente = new GradientPaint( 0, 0, colorFondo, 0, getHeight(), colorSuave );
        else
            gradiente = new GradientPaint( 0, 0, colorSuave, 0, getHeight(), colorFondo );
            
        
        g2.setPaint( gradiente );
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arco, arco);

        g2.setColor( colorBorde );
        g2.drawRoundRect(0, 0, getWidth( ) - 1, getHeight() - 1, arco, arco);

        g2.setStroke(new BasicStroke(10));
    }    
    private void escucharEventos(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(estadoEvento){
                    estaEncimaBoton = true;
                    repaint();
                }                
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if(estadoEvento){
                    estaEncimaBoton = false;
                    repaint();
                }                
            }
        });
    }
    public Limitacion getLimitacion() {
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion) {
        this.limitacion = limitacion;
        construirPanel();
    }
    public int getArco() {
        return arco;
    }
    public void setArco(int arco) {
        this.arco = arco;
        repaint();
    }
    public Color getColorFondo() {
        return colorFondo;
    }    
    public Color getColorSuave() {
        return colorSuave;
    }
    public Color getColorBorde() {
        return colorBorde;
    }
    public void setColorPanel(Color colorFondo, Color colorSuave, Color colorBorde){
        this.colorFondo = colorFondo;
        this.colorSuave = colorSuave;
        this.colorBorde = colorBorde;
        repaint();
    }
    public boolean isEstadoEvento() {
        return estadoEvento;
    }
    public void setEstadoEvento(boolean estadoEvento) {
        this.estadoEvento = estadoEvento;        
    }
}
