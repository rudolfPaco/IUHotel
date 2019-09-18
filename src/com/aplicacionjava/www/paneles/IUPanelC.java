/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.paneles;

import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.etiquetas.IUEtiquetaR;
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author rudolf
 */
public class IUPanelC extends IUPanel{
    
    public IUPanel panelTitulo;
    private String titulo;
    private final String descripcion;
    private IUPanelNota iuNombreTitulo;
    private IUEtiquetaR iuReloj;
    private IUEtiqueta iuFecha;
    
    public IUPanel panelFondo;
    
    public IUPanelC(Limitacion limitacion, String titulo, String descripcion) {
        super(limitacion);
        this.titulo = titulo;
        this.descripcion = descripcion;
        
        construirPaneles(getLimitacion());
    }
    private void construirPaneles(Limitacion limite){
        panelTitulo = new IUPanel(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(99), limite.getPorcentajeAlto(14)));
        panelTitulo.setBorder(new LineBorder(Color.RED));
        add(panelTitulo);
        construirPanelTitulo(panelTitulo.getLimitacion());
        
        panelFondo = new IUPanel(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(15), limite.getPorcentajeAncho(99), limite.getPorcentajeAlto(85)));
        panelFondo.setBorder(new LineBorder(Color.BLACK));
        add(panelFondo);
    }
    private void construirPanelTitulo(Limitacion limite){
        iuNombreTitulo = new IUPanelNota(titulo, descripcion, new Limitacion(limite.getPorcentajeAncho(0), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(40), limite.getPorcentajeAlto(98)), 25, 60, 51);        
        iuNombreTitulo.setBorder(new LineBorder(Color.yellow));
        panelTitulo.add(iuNombreTitulo);
        
        iuReloj = new IUEtiquetaR(new Limitacion(limite.getPorcentajeAncho(50), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(50), limite.getPorcentajeAlto(60)));
        iuReloj.setTipoLetraAMPM("MINUSCULA");
        panelTitulo.add(iuReloj);
        
        iuFecha = new IUEtiqueta(new Fecha().getFecha1(), new Limitacion(limite.getPorcentajeAncho(50), limite.getPorcentajeAlto(65), limite.getPorcentajeAncho(50), limite.getPorcentajeAlto(30)));
        iuFecha.setHorizontalAlignment(SwingConstants.CENTER);
        iuFecha.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(20)));
        panelTitulo.add(iuFecha);        
    }
    public void setTitulo(String titulo, String descripcion){
        iuNombreTitulo.iuTitulo.setText(titulo);
        iuNombreTitulo.iuTexto.setText(descripcion);
    }
}
