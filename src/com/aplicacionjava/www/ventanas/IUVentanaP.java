/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.ventanas;

import com.aplicacionjava.www.botones.IUBotonCM;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.paneles.IUPanelCD;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.recursos.Ventana;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author neo
 */
public class IUVentanaP extends JFrame implements Ventana{
    
    private int x = 0;
    private int y = 0;
    protected int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
    protected int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public IUPanelBD panelContenedor;
    
    private IUPanelCD panelTitulo;
    private int porcentajeAlturaTitulo;
    private final String titulo;
    private IUBotonCM botonMinimizar;
    private IUBotonCM botonCerrar;
    public IUEtiqueta iuTitulo;    
    
    public IUPanelBD panelFondo;
            
    public IUVentanaP(String titulo, int porcentajeAlturaTitulo){
        super();
        this.titulo = titulo;
        this.porcentajeAlturaTitulo = porcentajeAlturaTitulo;
        construirVentanaT();
    }
    public IUVentanaP(String titulo){
        super();
        this.titulo = titulo;
        this.porcentajeAlturaTitulo = 4;
        construirVentanaT();
    }
    
    private void construirVentanaT(){        
        setSize(new Dimension(ancho, alto));
        setUndecorated(true);
        setAlwaysOnTop(false);
        setLocationRelativeTo(this);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBackground(new Color(0, 0, 0, 0));
        
        construirComponentes(new Limitacion(ancho, alto));
        
        moverVentana();
        minimizarVentana();
        cerrarVentana();
    }
    private void construirComponentes(Limitacion limite){
        panelContenedor = new IUPanelBD(new Limitacion(limite.getAncho(), limite.getAlto()));
        panelContenedor.setArco(limite.getPorcentajeAlto(2));
        getContentPane().add(panelContenedor);
        
        panelTitulo = new IUPanelCD(new Limitacion(limite.getAncho(), limite.getPorcentajeAlto(porcentajeAlturaTitulo)));
        panelTitulo.setColorFondo(new Color(2, 67, 109), new Color(2, 67, 109), "", 1);
        panelContenedor.add(panelTitulo);
        construirPanelTitulo(panelTitulo.getLimitacion());
        
        panelFondo = new IUPanelBD(new Limitacion(0, limite.getPorcentajeAlto(porcentajeAlturaTitulo), limite.getAncho(), limite.getPorcentajeAlto(100 - porcentajeAlturaTitulo)));        
        panelFondo.setArco(0);        
        panelContenedor.add(panelFondo);        
    }
    private void construirPanelTitulo(Limitacion limite){
                
        iuTitulo = new IUEtiqueta(titulo, new Limitacion(limite.getAlto(), 0, limite.getAncho() - 3*limite.getAlto(), limite.getAlto()));
        iuTitulo.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(70)));
        iuTitulo.setForeground(Color.WHITE);        
        iuTitulo.setCursor(new Cursor(Cursor.HAND_CURSOR));        
        iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelTitulo.add(iuTitulo);
        
        botonMinimizar = new IUBotonCM("MINIMIZAR", new Limitacion(limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(12), limite.getPorcentajeAlto(80), limite.getPorcentajeAlto(80)));
        panelTitulo.add(botonMinimizar);
        
        botonCerrar = new IUBotonCM("CERRAR", new Limitacion(limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(12), limite.getPorcentajeAlto(80), limite.getPorcentajeAlto(80)));
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
                System.exit(0);
                botonCerrar.setEstaEncimaBoton(false);
            }
        });
    }

    @Override
    public void minimizarVentana() {
        botonMinimizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setExtendedState(JFrame.ICONIFIED);
            }
        });
    }

    @Override
    public void moverVentana() {
        iuTitulo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        
        iuTitulo.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point punto = MouseInfo.getPointerInfo().getLocation();
                setLocation(punto.x - x, punto.y - y);
            }
        });
    }
    public int getPorcentajeAlturaTitulo() {
        return porcentajeAlturaTitulo;
    }
    public void setPorcentajeAlturaTitulo(int porcentajeAlturaTitulo) {
        this.porcentajeAlturaTitulo = porcentajeAlturaTitulo;
        construirComponentes(new Limitacion(ancho, alto));
    }
    public void salirSistema(){
        System.exit(0);
    }
}
