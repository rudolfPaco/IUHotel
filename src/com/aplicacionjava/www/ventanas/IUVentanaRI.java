/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.ventanas;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.etiquetas.IUEtiquetaD;
import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelJS;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author rudolf
 */
public class IUVentanaRI extends IUVentanaT{

    private BufferedImage buffered;
    private boolean estado;
    
    private IUEtiquetaI iuImagen;
    private IUPanelJS primerPanel;
    private IUEtiquetaD recuadro;
    private IUBoton botonDibujarTamanoFoto;
    private IUBoton botonDibujarTamanoCarnet;
    private IUBoton botonDibujarTamanoPassaporte;
    private IUBoton botonDibujarTamanoCertificado;
    private JSlider redimensionarImagen;
    private IUBoton botonRecortarImagen;
    
    private IUPanel segundoPanel;
    
    public IUVentanaRI(JFrame ventanaPrincipal, String titulo, IUEtiquetaI iuImagen, BufferedImage buffered, Limitacion limitacion, int porcentajeAlturaTitulo) {
        super(ventanaPrincipal, titulo, limitacion, porcentajeAlturaTitulo);
        this.iuImagen = iuImagen;
        this.recuadro = null;
        this.buffered = buffered;
        this.estado = false;
        construirPaneles(panelFondo.getLimitacion());
        escucharEvento();
    }
    private void construirPaneles(Limitacion limite){
        primerPanel = new IUPanelJS(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(70), limite.getPorcentajeAlto(98)));
        panelFondo.add(primerPanel.deslizador);
        construirPrimerPanel(primerPanel.getLimitacion());
        
        segundoPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(72), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(27), limite.getPorcentajeAlto(98)));
        panelFondo.add(segundoPanel);
        construirSegundoPanel(segundoPanel.getLimitacion());
        dibujarImagen();
    }
    private void construirPrimerPanel(Limitacion limite){
        
    }
    private void construirSegundoPanel(Limitacion limite){
        botonDibujarTamanoFoto = new IUBoton("dibujar tam foto", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(5)));
        //segundoPanel.add(botonDibujarTamanoFoto);
        
        botonDibujarTamanoCarnet = new IUBoton("dibujar tam carnet", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(13), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(5)));
        //segundoPanel.add(botonDibujarTamanoCarnet);
        
        botonDibujarTamanoPassaporte = new IUBoton("dibujar tam passaporte", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(21), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(5)));
        //segundoPanel.add(botonDibujarTamanoPassaporte);
        
        botonDibujarTamanoCertificado = new IUBoton("dibujar tam certificado", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(29), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(5)));
        //segundoPanel.add(botonDibujarTamanoCertificado);
        
        redimensionarImagen = new JSlider(JSlider.HORIZONTAL, 10, 100, 100);
        redimensionarImagen.setMajorTickSpacing(10);
        redimensionarImagen.setMinorTickSpacing(5);
        redimensionarImagen.setPaintTicks(true);
        redimensionarImagen.setPaintLabels(true);
        redimensionarImagen.setBounds(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(70), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(6));        
        segundoPanel.add(redimensionarImagen);
        redimensionarImagen.addChangeListener((ChangeEvent e) -> {
            if(!redimensionarImagen.getValueIsAdjusting()) {
                recuadro.repaint();
            }
        });
        
        botonRecortarImagen = new IUBoton("recortar imagen", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(80), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(15)));
        segundoPanel.add(botonRecortarImagen);
    }
    private void dibujarImagen(){
        recuadro = new IUEtiquetaD(redimensionarImagen, buffered);
        primerPanel.add(recuadro);
        primerPanel.setPreferredSize(new Dimension(buffered.getWidth(), buffered.getHeight()));
        primerPanel.updateUI();
    }
    private void escucharEvento(){
        botonRecortarImagen.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                recuadro.recortar(iuImagen);
                estado = true;
                dispose();
            }
        });
    }
    public boolean getEstado(){
        return estado;
    }
}