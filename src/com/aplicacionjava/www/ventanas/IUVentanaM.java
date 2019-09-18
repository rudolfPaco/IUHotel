/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.ventanas;

import com.aplicacionjava.www.botones.IUBotonIT;
import com.aplicacionjava.www.campos.IUAreaTexto;
import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

/**
 *
 * @author neo
 */
public class IUVentanaM extends IUVentanaT{
        
    private String tipoMensaje;
    private String mensaje;
    
    private IUPanel primerPanel;
    private IUEtiquetaI iuImagen;
    
    private IUPanel segundoPanel;
    private IUAreaTexto iuMensaje;
    
    private IUPanel tercerPanel;
    private IUBotonIT botonNo;
    private IUBotonIT botonSi;
    private IUBotonIT botonAceptar;
    
    private boolean estado;
    
    public IUVentanaM(JFrame ventanaPrincipal, Limitacion limitacion, String tipoMensaje, String mensaje, String titulo, int alturaTitulo) {
        super(ventanaPrincipal, titulo, limitacion, alturaTitulo);
        this.tipoMensaje = tipoMensaje;
        this.mensaje = mensaje;
        this.estado = false;
        
        construirPaneles(panelFondo.getLimitacion());
        setTipoMensaje();
        setEventos();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(0), limite.getPorcentajeAlto(0), limite.getAncho(), limite.getPorcentajeAlto(20)));
        //primerPanel.setBorder(new LineBorder(Color.yellow));
        panelFondo.add(primerPanel);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(0), limite.getPorcentajeAlto(20), limite.getAncho(), limite.getPorcentajeAlto(60)));
        //segundoPanel.setBorder(new LineBorder(Color.yellow));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
        
        tercerPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(0), limite.getPorcentajeAlto(80), limite.getAncho(), limite.getPorcentajeAlto(20)));
        //tercerPanel.setBorder(new LineBorder(Color.yellow));
        panelFondo.add(tercerPanel);
        construirTercerPanel(tercerPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){        
        iuImagen = new IUEtiquetaI("src/imagenes/peligro.png", new Limitacion(limite.getPorcentajeAncho(5), limite.getPorcentajeAlto(5), limite.getPorcentajeAlto(90), limite.getPorcentajeAlto(90)));
        //iuImagen.setBorder(new LineBorder(Color.yellow));
        primerPanel.add(iuImagen);
    }
    private void construirSegundoPanel(Limitacion limite){
        iuMensaje = new IUAreaTexto(mensaje, new Limitacion(limite.getPorcentajeAncho(10), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(80), limite.getPorcentajeAlto(90)));
        iuMensaje.setFont(new Font("Verdana", Font.PLAIN, 20));
        iuMensaje.setEditable(false);
        iuMensaje.setBorder(null);
        segundoPanel.add(iuMensaje);
    }
    private void construirTercerPanel(Limitacion limite){
        botonAceptar = new IUBotonIT("aceptar", "src/imagenes/aceptar.png", new Limitacion(limite.getPorcentajeAncho(30), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(60)));
        botonAceptar.iuTexto.setFont(new Font("Verdana", Font.PLAIN, 18));
        botonAceptar.setVisible(false);
        botonAceptar.setFocusable(true);
        botonAceptar.iuTexto.setFocusable(true);
        botonAceptar.iuImagen.setFocusable(true);
        tercerPanel.add(botonAceptar);
        
        botonNo = new IUBotonIT("No", "src/imagenes/cancelar.png", new Limitacion(limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(60)));
        botonNo.iuTexto.setFont(new Font("Verdana", Font.PLAIN, 20));
        botonNo.setVisible(false);
        botonNo.setFocusable(true);
        botonNo.iuTexto.setFocusable(true);
        botonNo.iuImagen.setFocusable(true);
        tercerPanel.add(botonNo);
        
        botonSi = new IUBotonIT("Si", "src/imagenes/si.png", new Limitacion(limite.getPorcentajeAncho(60), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(20), limite.getPorcentajeAlto(60)));
        botonSi.iuTexto.setFont(new Font("Verdana", Font.PLAIN, 20));
        botonSi.setVisible(false);
        botonSi.setFocusable(true);
        botonSi.iuTexto.setFocusable(true);
        botonSi.iuImagen.setFocusable(true);
        tercerPanel.add(botonSi);
    }
    private void setTipoMensaje(){
        switch(tipoMensaje){
            case "informacion":
                iuImagen.setUrlImagen("src/imagenes/informacion.png");
                botonAceptar.setVisible(true);
            break;
            case "aviso":
                iuImagen.setUrlImagen("src/imagenes/aviso.png");
                botonAceptar.setVisible(true);
            break;
            case "peligro":
                iuImagen.setUrlImagen("src/imagenes/peligro.png");
                botonSi.setVisible(true);
                botonNo.setVisible(true);
            break;
            case "eliminar":
                iuImagen.setUrlImagen("src/imagenes/eliminar.png");
                botonSi.setVisible(true);
                botonNo.setVisible(true);
            break;
            case "correcto":
                iuImagen.setUrlImagen("src/imagenes/manitoArriba.png");
                botonAceptar.setVisible(true);
            break;
        }
    }
    private void setEventos(){
        iuMensaje.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(KeyEvent.VK_ENTER == e.getKeyCode()){
                    estado = true;
                    dispose();
                }
                if(KeyEvent.VK_ESCAPE == e.getKeyCode()){
                    estado = false;
                    dispose();
                }
            }
        });
        botonAceptar.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                estado = true;
                dispose();
            }
        });
        
        botonSi.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                estado = true;
                dispose();
            }
        });
        
        botonNo.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
    }
    public boolean getEstado(){
        return estado;
    }
}
