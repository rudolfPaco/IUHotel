/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.paneles;

import com.aplicacionjava.www.campos.IUCampoTexto;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

/**
 *
 * @author hotel-felipez
 */
public class IUPanelS extends IUPanel{
    
    public IUEtiqueta iuTitulo;
    public IUCampoTexto iuTexto;
    public IUEtiqueta botonArriba;
    public IUEtiqueta botonAbajo;
    
    private String titulo;
    private String texto;
    private int porcentajeAltoTitulo;
    private int porcentajeAltoTexto;
    private int porcentajeAnchuraBotones;
    
    private int inicio;
    private int paso;
    private int fin;
    
    public IUPanelS(String titulo, int inicio, int paso, int fin, Limitacion limitacion, int porcentajeAltoTitulo, int porcentajeAltoTexto, int porcentajeAnchuraUnidad) {
        super(limitacion);
        this.titulo = titulo;
        this.texto = String.valueOf(inicio);
        this.inicio = inicio;
        this.paso = paso;
        this.fin = fin;
        this.porcentajeAltoTitulo = porcentajeAltoTitulo;
        this.porcentajeAltoTexto = porcentajeAltoTexto;
        this.porcentajeAnchuraBotones = porcentajeAnchuraUnidad;
        construirPanelCTU(getLimitacion());
    }
    private void construirPanelCTU(Limitacion limite){
        iuTitulo = new IUEtiqueta(titulo, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3, limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(porcentajeAltoTitulo)));
        iuTitulo.setForeground(new Color(120, 120, 120));
        iuTitulo.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(porcentajeAltoTitulo - porcentajeAltoTitulo/4)));
        add(iuTitulo);
        
        iuTexto = new IUCampoTexto(texto, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3 + limite.getPorcentajeAlto(porcentajeAltoTitulo), limite.getPorcentajeAncho(96 - porcentajeAnchuraBotones), limite.getPorcentajeAlto(porcentajeAltoTexto)));        
        iuTexto.setFont(new Font("Verdana", Font.PLAIN, iuTexto.getLimitacion().getPorcentajeAlto(60)));
        add(iuTexto);
        
        botonArriba = new IUEtiqueta("▲", new Limitacion(limite.getPorcentajeAncho(97) - limite.getPorcentajeAncho(porcentajeAnchuraBotones), limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3 + limite.getPorcentajeAlto(porcentajeAltoTitulo), limite.getPorcentajeAncho(porcentajeAnchuraBotones), limite.getPorcentajeAlto(porcentajeAltoTexto)/2));
        botonArriba.setFont(new Font("Arial", Font.PLAIN, iuTexto.getLimitacion().getPorcentajeAlto(porcentajeAltoTexto - porcentajeAltoTexto/7)));
        botonArriba.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(botonArriba);
        
        botonAbajo = new IUEtiqueta("▼", new Limitacion(limite.getPorcentajeAncho(97) - limite.getPorcentajeAncho(porcentajeAnchuraBotones), limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3 + limite.getPorcentajeAlto(porcentajeAltoTitulo) + limite.getPorcentajeAlto(porcentajeAltoTexto)/2, limite.getPorcentajeAncho(porcentajeAnchuraBotones), limite.getPorcentajeAlto(porcentajeAltoTexto)/2));
        botonAbajo.setFont(new Font("Arial", Font.PLAIN, iuTexto.getLimitacion().getPorcentajeAlto(porcentajeAltoTexto - porcentajeAltoTexto/7)));
        botonAbajo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(botonAbajo);
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
        iuTitulo.setText(titulo);
    }
    public String getTexto() {
        texto = iuTexto.getText();
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
        iuTexto.setText(texto);
    }
    public int getPorcentajeAltoTitulo() {
        return porcentajeAltoTitulo;
    }
    public void setPorcentajeAltoTitulo(int porcentajeAltoTitulo) {
        this.porcentajeAltoTitulo = porcentajeAltoTitulo;
        construirPanelCTU(getLimitacion());
    }
    public int getPorcentajeAltoTexto() {
        return porcentajeAltoTexto;
    }
    public void setPorcentajeAltoTexto(int porcentajeAltoTexto) {
        this.porcentajeAltoTexto = porcentajeAltoTexto;
        construirPanelCTU(getLimitacion());
    }
    public int getPorcentajeAnchuraUnidad() {
        return porcentajeAnchuraBotones;
    }
    public void setPorcentajeAnchuraUnidad(int porcentajeAnchuraUnidad) {
        this.porcentajeAnchuraBotones = porcentajeAnchuraUnidad;
        construirPanelCTU(getLimitacion());
    }    
    public void setFuente(Font fuenteTitulo, Font fuenteTextoUnidad){
        iuTitulo.setFont(fuenteTitulo);
        iuTexto.setFont(fuenteTextoUnidad);        
    }
    public void setEventoRaton(MouseAdapter mouse){
        addMouseListener(mouse);
        iuTitulo.addMouseListener(mouse);
        iuTitulo.setCursor(new Cursor(Cursor.HAND_CURSOR));        
        iuTexto.addMouseListener(mouse);
        iuTexto.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }    
    public void presionoBotonArriba(){
        if(!iuTexto.getText().isEmpty()){
            int numero = Integer.parseInt(iuTexto.getText());
            if(numero >= inicio && numero + paso <= fin){
                numero = numero + paso;
                iuTexto.setText(String.valueOf(numero));
            }
        }
    }
    public void presionoBotonAbajo(){
        if(!iuTexto.getText().isEmpty()){
            int numero = Integer.parseInt(iuTexto.getText());
            if(numero - paso >= inicio && paso <= fin){
                numero = numero - paso;
                iuTexto.setText(String.valueOf(numero));
            }
        }                
    }
}
