/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.botones;

import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

/**
 *
 * @author rudolf
 */
public class IUBoton extends JComponent{
    public IUEtiqueta iuTexto;
    
    private String texto;
    
    private boolean estaEncimaBoton;
    
    private Limitacion limitacion;    
    private int arco;
    private final int resaltador = 10;
    
    private Color colorDebil = new Color(250, 250, 250);
    private Color colorFuerte = new Color(242, 238, 236);    
    private Color colorBorde = new Color(170, 170, 170);
    
    private MouseAdapter eventoRaton;
    
    /**
     * Interfaz de Usuario Boton Image y Texto, que hereda del JComponent, tiene un color degradado y un borde redondeado, los cuales es posible modificar la forma y el color del boton.
     * @param texto es la cadena de caracteres que se mostrara en el boton.
     * @param limitacion determina la posicion y el tamaño del componente.
     */
    public IUBoton(String texto, Limitacion limitacion) {
        this.texto = texto;
        this.limitacion = limitacion;
        this.estaEncimaBoton = false;        
        
        construirBoton();
        escucharEventos();
    }
    private void construirBoton(){
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        setArco(limitacion.getPorcentajeAlto(5) + limitacion.getPorcentajeAncho(5));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFocusable(false);
        setOpaque(false);
        setBorder(null);
        
        construirComponentes();
    }
    private void construirComponentes(){
        iuTexto = new IUEtiqueta(texto, new Limitacion(limitacion.getPorcentajeAncho(2), limitacion.getPorcentajeAlto(5), limitacion.getPorcentajeAncho(96), limitacion.getPorcentajeAlto(90)));
        iuTexto.setHorizontalAlignment(SwingConstants.CENTER);
        add(iuTexto);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gradiente;
        
        if ( estaEncimaBoton )
            gradiente = new GradientPaint( 0, 0, colorFuerte, 0, getHeight(), colorDebil );
        else
            gradiente = new GradientPaint( 0, 0, colorDebil, 0, getHeight(), colorFuerte );
                
        g2.setPaint( gradiente );
        g2.fillRoundRect(0, 0, getWidth( ), getHeight(), arco, arco);

        g2.setColor( colorBorde );
        g2.drawRoundRect(0, 0, getWidth( ) - 1, getHeight() - 1, arco, arco);

        g2.setStroke(new BasicStroke(resaltador));
    }
    private void escucharEventos(){
        eventoRaton = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                estaEncimaBoton = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                estaEncimaBoton = false;
                repaint();
            }            
        };   
        addMouseListener(eventoRaton);
        iuTexto.addMouseListener(eventoRaton);
    }
    /**
     * cambia los colores del boton empezando del color blanco hasta el color guindo.
     * @param colorDebil esta en la parte superior del boton.
     * @param colorFuerte esta en la parte media del boton.
     * @param colorBorde esta en la parte inferior del boton.
     */
    public void setColores(Color colorFuerte, Color colorDebil, Color colorBorde){
        this.colorDebil = colorDebil;
        this.colorFuerte = colorFuerte;        
        this.colorBorde = colorBorde;
        repaint();
    }    
    public Limitacion getLimitacion() {
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion) {
        this.limitacion = limitacion;
        repaint();
    }
    public int getArco() {
        return arco;
    }
    public void setArco(int arco) {
        this.arco = arco;
        repaint();
    }
    public void setFuente(Font fuente){
        iuTexto.setFont(fuente);
    }
    public void setTextoMuestra(String informacion){
        iuTexto.setToolTipText(informacion);        
        setToolTipText(informacion);
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
        iuTexto.setText(texto);
    }
    public void addEventoRaton(MouseAdapter evento){
        addMouseListener(evento);
        iuTexto.addMouseListener(evento);
    }
}
