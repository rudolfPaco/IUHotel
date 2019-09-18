/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.paneles;

import com.aplicacionjava.www.campos.IUCampoTextoU;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;

/**
 *
 * @author rudolf
 */
public class IUPanelCTU extends IUPanel{
        
    public IUEtiqueta iuTitulo;
    public IUCampoTextoU iuTexto;
    
    private String titulo;
    private String texto;
    private String unidad;
    private int porcentajeAltoTitulo;
    private int porcentajeAltoTexto;
    private int porcentajeAnchuraUnidad;
    
    public IUPanelCTU(String titulo, String texto, String unidad, Limitacion limitacion, int porcentajeAltoTitulo, int porcentajeAltoTexto, int porcentajeAnchuraUnidad) {
        super(limitacion);
        this.titulo = titulo;
        this.texto = texto;
        this.unidad = unidad;
        this.porcentajeAltoTitulo = porcentajeAltoTitulo;
        this.porcentajeAltoTexto = porcentajeAltoTexto;
        this.porcentajeAnchuraUnidad = porcentajeAnchuraUnidad;
        construirPanelCTU(getLimitacion());
    }
    private void construirPanelCTU(Limitacion limite){
        iuTitulo = new IUEtiqueta(titulo, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3, limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(porcentajeAltoTitulo)));
        iuTitulo.setForeground(new Color(120, 120, 120));
        iuTitulo.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(porcentajeAltoTitulo - porcentajeAltoTitulo/4)));
        add(iuTitulo);
        
        iuTexto = new IUCampoTextoU(texto, unidad, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3 + limite.getPorcentajeAlto(porcentajeAltoTitulo), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(porcentajeAltoTexto)), porcentajeAnchuraUnidad);
        iuTexto.setFont(new Font("Verdana", Font.PLAIN, iuTexto.getLimitacion().getPorcentajeAlto(60)));
        add(iuTexto);
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
    public String getUnidad() {
        return unidad;
    }
    public void setUnidad(String unidad) {
        this.unidad = unidad;
        iuTexto.setUnidad(unidad);
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
        return porcentajeAnchuraUnidad;
    }
    public void setPorcentajeAnchuraUnidad(int porcentajeAnchuraUnidad) {
        this.porcentajeAnchuraUnidad = porcentajeAnchuraUnidad;
        construirPanelCTU(getLimitacion());
    }    
    public void setFuente(Font fuenteTitulo, Font fuenteTextoUnidad){
        iuTitulo.setFont(fuenteTitulo);
        iuTexto.setFuente(fuenteTextoUnidad);        
    }
    public void setEventoRaton(MouseAdapter mouse){
        addMouseListener(mouse);
        iuTitulo.addMouseListener(mouse);
        iuTitulo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iuTexto.iuUnidad.addMouseListener(mouse);
        iuTexto.iuUnidad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iuTexto.addMouseListener(mouse);
        iuTexto.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
