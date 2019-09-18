/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.ventanas;

import com.aplicacionjava.www.botones.IUBotonCM;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.recursos.Ventana;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author rudolf
 */
public class IUVentanaF extends JFrame implements Ventana{
    private Color colorTitulo;
    private int x = 0;
    private int y = 0;
    private Limitacion limitacion;
    
    public IUPanelBD panelContenedor;
    
    private IUPanel panelTitulo;
    private int porcentajeAlturaTitulo;
    private final String titulo;
    private IUBotonCM botonCerrar;
    public IUEtiqueta etiquetaTitulo;    
    
    public IUPanelBD panelFondo;
            
    public IUVentanaF(String titulo, Limitacion limitacion, int porcentajeAlturaTitulo){
        super();
        this.titulo = titulo;
        this.limitacion = limitacion;
        this.porcentajeAlturaTitulo = porcentajeAlturaTitulo;
        construirVentanaT();
    }
    
    private void construirVentanaT(){     
        setSize(new Dimension(limitacion.getAncho(), limitacion.getAlto()));
        setUndecorated(true);
        setAlwaysOnTop(false);        
        setLocationRelativeTo(this);        
        setBackground(new Color(0, 0, 0, 0));
        setColorTitulo(new Color(180, 0, 0));
        setLayout(null);
        construirComponentes();
        moverVentana();
        cerrarVentana();
    }
    private void construirComponentes(){
        panelContenedor = new IUPanelBD(new Limitacion(limitacion.getAncho(), limitacion.getAlto()));
        panelContenedor.setArco(limitacion.getPorcentajeAlto(2));
        getContentPane().add(panelContenedor);
        
        panelTitulo = new IUPanel(new Limitacion(limitacion.getAncho(), limitacion.getPorcentajeAlto(porcentajeAlturaTitulo)));
        panelContenedor.add(panelTitulo);
        construirPanelTitulo(panelTitulo.getLimitacion());
        
        panelFondo = new IUPanelBD(new Limitacion(0, limitacion.getPorcentajeAlto(porcentajeAlturaTitulo), limitacion.getAncho(), limitacion.getPorcentajeAlto(100 - porcentajeAlturaTitulo)));        
        panelFondo.setArco(0);        
        panelContenedor.add(panelFondo);        
    }
    private void construirPanelTitulo(Limitacion limite){
        etiquetaTitulo = new IUEtiqueta(titulo, new Limitacion(limite.getAncho() - limite.getAlto(), limite.getAlto()));
        etiquetaTitulo.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(75)));        
        etiquetaTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaTitulo.setForeground(new Color(180, 0, 0));
        etiquetaTitulo.setCursor(new Cursor(Cursor.HAND_CURSOR));        
        panelTitulo.add(etiquetaTitulo);
        
        botonCerrar = new IUBotonCM("CERRAR", new Limitacion(etiquetaTitulo.getLimitacion().getAncho() + limite.getPorcentajeAlto(10), limite.getPorcentajeAlto(10), limite.getPorcentajeAlto(80), limite.getPorcentajeAlto(80)));
        panelTitulo.add(botonCerrar);        
    }
        
    @Override
    public void mostrarVentana() {
        setVisible(true);
    }

    @Override
    public void cerrarVentana() {
        botonCerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
                botonCerrar.setEstaEncimaBoton(false);
            }
        });
    }

    @Override
    public void minimizarVentana() {
    }

    @Override
    public void moverVentana() {
        etiquetaTitulo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        
        etiquetaTitulo.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point punto = MouseInfo.getPointerInfo().getLocation();
                setLocation(punto.x - x, punto.y - y);
            }
        });
    }
    public Limitacion getLimitacion(){
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion){
        this.limitacion = limitacion;
        construirVentanaT();
    }
    public Color getColorTitulo() {
        return colorTitulo;
    }
    public void setColorTitulo(Color colorTitulo) {
        this.colorTitulo = colorTitulo;
        setForeground(colorTitulo);
    }
    public int getPorcentajeAlturaTitulo() {
        return porcentajeAlturaTitulo;
    }
    public void setPorcentajeAlturaTitulo(int porcentajeAlturaTitulo) {
        this.porcentajeAlturaTitulo = porcentajeAlturaTitulo;
        construirComponentes();
    }
}
