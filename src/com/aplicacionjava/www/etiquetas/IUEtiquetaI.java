/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.etiquetas;

import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author rudolf
 */
public class IUEtiquetaI extends JLabel{
    private String urlImagen;
    private Limitacion limitacion;
    private BufferedImage buffered;
    private Object objeto;
    private int inicioX;
    private int inicioY;
    private int x;
    private int y;
    private int moviendoX;
    private int moviendoY;
        
    /**
     * Interfaz de Usuario Etiqueta Imagen, hereda atributos y metodos del componente JLabel.
     * @param urlImagen direccion de la imagen guardad en el disco.
     * @param limitacion determina la posicion y el tamaño del componente.
     */
    public IUEtiquetaI(String urlImagen, Limitacion limitacion){
        this.urlImagen = urlImagen;
        this.limitacion = limitacion;
        this.buffered = null;
        this.objeto = null;
        this.x = limitacion.getX();
        this.y = limitacion.getY();
        construirEtiqueta();        
    }
    private void construirEtiqueta(){
        setOpaque(false);
        setToolTipText("");
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setFocusable(false);
        construirImagen();
    }
    private void construirImagen(){
        if(!urlImagen.isEmpty()){
            if (getClass().getResource(urlImagen) != null) {
                setIcon(new ImageIcon(new ImageIcon(getClass().getResource(urlImagen)).getImage().getScaledInstance(limitacion.getAncho(), limitacion.getAlto(), Image.SCALE_SMOOTH)));
            } else {
                setIcon(new ImageIcon(new ImageIcon(urlImagen).getImage().getScaledInstance(limitacion.getAncho(), limitacion.getAlto(), Image.SCALE_SMOOTH)));
            }
        }
    }

    public Limitacion getLimitacion(){
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion){
        this.limitacion = limitacion;
        construirEtiqueta();
    }
    public void setUrlImagen(String urlImagen){
        this.urlImagen = urlImagen;
        construirImagen();
    }
    public String getUrlImagen(){
        return urlImagen;
    }
    public BufferedImage getBuffered() {
        return buffered;
    }
    public void setBuffered(BufferedImage buffered) {
        this.buffered = buffered;        
    }
    public Object getObjeto() {
        return objeto;
    }
    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
    public void setMovimiento(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                inicioX = e.getX();
                inicioY = e.getY();                
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                moviendoX = e.getX();
                moviendoY = e.getY();

                int nuevaPosicionX = x + moviendoX - inicioX;
                int nuevaPosicionY = y + moviendoY - inicioY;

                // Movemos la etiqueta a la nueva posición
                setLocation( nuevaPosicionX,nuevaPosicionY );
                // Guarda la posición actual de la etiqueta
                x = nuevaPosicionX;
                y = nuevaPosicionY;
            }
        });
    }
}