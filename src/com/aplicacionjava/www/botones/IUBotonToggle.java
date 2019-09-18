/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.botones;

import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
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
public class IUBotonToggle extends JComponent{
    public IUEtiquetaI iuImagen;
    public IUEtiqueta iuTexto;
    
    private String url;
    private String texto;
    
    private boolean estaEncimaBoton;
    
    private Limitacion limitacion;    
    private boolean presionado;
    private int arco;
    private final int resaltador = 10;
        
    private Color colorLetraPresionado = new Color(100, 100, 100);
    private Color colorLetra = new Color(2, 67, 109);
    
    private Color colorFuerte = new Color(242, 238, 236);
    private Color colorDebil = new Color(250, 250, 250 );
    private Color colorBorde = new Color(170, 170, 170);
    
    
    private MouseAdapter eventoRaton;
    
    /**
     * Interfaz de Usuario Boton Image y Texto, que hereda del JComponent, tiene un color degradado y un borde redondeado, los cuales es posible modificar la forma y el color del boton.
     * @param texto es la cadena de caracteres que se mostrara en el boton.
     * @param url hace referencia a la imagen que esta guardada en el disco.
     * @param limitacion determina la posicion y el tamaño del componente.
     * @param presionado determina si el boton va a estar presiono al momento de crearse.
     */
    public IUBotonToggle(String texto, String url, Limitacion limitacion, boolean presionado) {
        this.texto = texto;
        this.url = url;
        this.limitacion = limitacion;
        this.estaEncimaBoton = false;        
        
        construirBoton();
        escucharEventos();
        setPresionado(presionado);
    }
    private void construirBoton(){
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        setArco(10);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFocusable(false);
        setOpaque(false);
        setBorder(null);
        
        construirComponentes();
    }
    private void construirComponentes(){        
        iuImagen = new IUEtiquetaI(url, new Limitacion(limitacion.getPorcentajeAncho(2), limitacion.getPorcentajeAlto(5), limitacion.getPorcentajeAlto(90), limitacion.getPorcentajeAlto(90)));
        add(iuImagen);
        
        iuTexto = new IUEtiqueta(texto, new Limitacion(iuImagen.getLimitacion().getX() + iuImagen.getLimitacion().getAncho() + limitacion.getPorcentajeAncho(1), limitacion.getPorcentajeAlto(15), limitacion.getPorcentajeAncho(95) - limitacion.getPorcentajeAlto(90), limitacion.getPorcentajeAlto(70)));
        iuTexto.setHorizontalAlignment(SwingConstants.LEFT);
        iuTexto.setFont(new Font("Verdana", Font.PLAIN, limitacion.getPorcentajeAlto(45)));
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
                if(presionado){                    
                    iuTexto.setForeground(colorLetraPresionado);
                }
                estaEncimaBoton = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!presionado){
                    iuTexto.setForeground(colorLetra);
                }                
                estaEncimaBoton = false;
                repaint();
            }            
        };   
        addMouseListener(eventoRaton);
        iuImagen.addMouseListener(eventoRaton);
        iuTexto.addMouseListener(eventoRaton);
    }
    /**
     * cambia los colores del boton empezando del color blanco hasta el color guindo.
     * @param colorDebil esta en la parte superior del boton.
     * @param colorFuerte esta en la parte media del boton.
     * @param colorBorde esta en la parte inferior del boton.
     */
    public void setColores(Color colorFuerte, Color colorDebil, Color colorBorde){
        this.colorFuerte = colorFuerte;
        this.colorDebil = colorDebil;        
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
        iuImagen.setToolTipText(informacion);
        iuTexto.setToolTipText(informacion);        
        setToolTipText(informacion);
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
        iuImagen.setUrlImagen(url);        
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
        iuImagen.addMouseListener(evento);
        iuTexto.addMouseListener(evento);
    }
    public boolean isPresionado() {
        return presionado;
    }
    public void setPresionado(boolean presionado) {
        this.presionado = presionado;
        if(presionado){
            iuTexto.setForeground(colorLetraPresionado);
            setColorBorde(new Color(170, 170, 170));            
        }else{
            iuTexto.setForeground(colorLetra);
            setColorBorde(colorFuerte);
        }
    }    
    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
        repaint();
    }
}
