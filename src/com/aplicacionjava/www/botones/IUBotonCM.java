/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.botones;

import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

/**
 *
 * @author rudolf
 */
public class IUBotonCM extends JComponent{
    
    private static final int MARGEN = 5;
    private static final int RESALTADOR = 2;
    private static final int ARCO = 10;
    private boolean estaEncimaBoton;    
    private String tipoBoton;
    private Limitacion limitacion;
    
    private Color colorBlanco = new Color(255, 255, 255);
    private Color colorRosado = new Color( 240, 200, 200 );
    private Color colorRojo = new Color( 240, 0, 0 );    
    private Color colorGuindo = new Color( 200, 0, 0 );
    
    /** 
     * boton con un color degradado, tiene dos funcionalidades: boton Minimizar o boton Cerrar.
     * @param tipoBoton establece el tipo del boton que se va a dibujar: MINIMIZAR (con una linea en la parte inferior del boton) o CERRAR (dibujado una X en el centro del boton)
     * @param limitacion determina la posicion y el tamaño del componente.
     */
    public IUBotonCM(String tipoBoton, Limitacion limitacion) {
        this.tipoBoton = tipoBoton;
        this.limitacion = limitacion;
        this.estaEncimaBoton = false;
        
        construirBotonDibujado();
        escucharEventos();
    }
    private void construirBotonDibujado(){
        this.enableInputMethods(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));        
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gradiente = new GradientPaint( 0, 0, colorRosado, 0, getHeight(), colorRojo );
        
        if ( estaEncimaBoton )
            gradiente = new GradientPaint( 0, 0, colorRosado.brighter(), 0, getHeight(), colorRojo.brighter() );
        else
            gradiente = new GradientPaint( 0, 0, colorRosado, 0, getHeight(), colorRojo );
            
        
        g2.setPaint( gradiente );
        g2.fillRoundRect(0, 0, getWidth( ), getHeight(), ARCO, ARCO);

        g2.setColor( colorGuindo );
        g2.drawRoundRect(0, 0, getWidth( ) - 1, getHeight() - 1, ARCO, ARCO);

        g2.setStroke(new BasicStroke(RESALTADOR));

        if(tipoBoton.equalsIgnoreCase("MINIMIZAR")){
            g2.drawLine(MARGEN, getHeight() - MARGEN, getWidth() - MARGEN, getHeight() - MARGEN);                            
            g2.setColor(colorBlanco);
            g2.drawLine(MARGEN, getHeight() - MARGEN - 1, getWidth() - MARGEN, getHeight() - MARGEN - 1);
        }else{
            if(tipoBoton.equalsIgnoreCase("CERRAR")){
                g2.drawLine(MARGEN, MARGEN - 1, getWidth() - MARGEN, getHeight() - MARGEN - 1);
                g2.drawLine(getWidth() - MARGEN, MARGEN - 1, MARGEN, getHeight() - MARGEN - 1);
                g2.setColor(colorBlanco);
                g2.drawLine(MARGEN, MARGEN, getWidth() - MARGEN, getHeight() - MARGEN);
                g2.drawLine(getWidth() - MARGEN, MARGEN, MARGEN, getHeight() - MARGEN);
            }
        }        
    }
    private void escucharEventos(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                estaEncimaBoton = true;
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                estaEncimaBoton = false;
                repaint();
            }
        });
    }

    /**
     * cambia los colores del boton empezando del color blanco hasta el color guindo.
     * @param colorBlanco pinta la linea o la X del boton.
     * @param colorRosado esta en la parte superior del boton.
     * @param colorRojo esta en la parte media del boton.
     * @param colorGuindo esta en la parte inferior del boton.
     */
    public void setColores(Color colorBlanco, Color colorRosado, Color colorRojo, Color colorGuindo){
        this.colorBlanco = colorBlanco;
        this.colorRosado = colorRosado;
        this.colorRojo = colorRojo;        
        this.colorGuindo = colorGuindo;
        repaint();
    }
    public Limitacion getLimite() {
        return limitacion;
    }
    public void setLimite(Limitacion limite) {
        this.limitacion = limite;
        construirBotonDibujado();
    }
    public String getTipoBoton() {
        return tipoBoton;
    }
    public void setTipoBoton(String tipoBoton) {
        this.tipoBoton = tipoBoton;
        repaint();
    }
    public void setEstaEncimaBoton(boolean estaEncimaBoton) {
        this.estaEncimaBoton = estaEncimaBoton;
        repaint();
    }
}
