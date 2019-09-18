/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.paneles;

import com.aplicacionjava.www.campos.IUAreaTexto;
import com.aplicacionjava.www.campos.IUCampoTexto;
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
public class IUPanelTA extends IUPanel{
    
    public IUEtiqueta iuTitulo;
    public IUAreaTexto iuAreaTexto;
    
    private String titulo;
    private String areaTexto;
    private int porcentajeAltoTitulo;
    private int porcentajeAltoTexto;
    
    public IUPanelTA(String titulo, String areaTexto, Limitacion limitacion, int porcentajeAltoTitulo, int porcentajeAltoTexto) {
        super(limitacion);
        this.titulo = titulo;
        this.areaTexto = areaTexto;
        this.porcentajeAltoTitulo = porcentajeAltoTitulo;
        this.porcentajeAltoTexto = porcentajeAltoTexto;
        construirPanelCT(getLimitacion());
    }
    private void construirPanelCT(Limitacion limite){
        iuTitulo = new IUEtiqueta(titulo, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3, limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(porcentajeAltoTitulo)));
        iuTitulo.setForeground(new Color(120, 120, 120));
        iuTitulo.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(porcentajeAltoTitulo - porcentajeAltoTitulo/4)));
        add(iuTitulo);
        
        iuAreaTexto = new IUAreaTexto(areaTexto, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3 + limite.getPorcentajeAlto(porcentajeAltoTitulo), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(porcentajeAltoTexto)));        
        //iuAreaTexto.setFont(new Font("Verdana", Font.PLAIN, iuAreaTexto.getLimitacion().getAlto()));
        add(iuAreaTexto);
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
        iuTitulo.setText(titulo);
    }
    public String getAreaTexto() {
        areaTexto = iuAreaTexto.getText();
        return areaTexto;
    }
    public void setAreaTexto(String texto) {
        this.areaTexto = texto;
        iuAreaTexto.setText(texto);
    }
    public int getPorcentajeAltoTitulo() {
        return porcentajeAltoTitulo;
    }
    public void setPorcentajeAltoTitulo(int porcentajeAltoTitulo) {
        this.porcentajeAltoTitulo = porcentajeAltoTitulo;
        construirPanelCT(getLimitacion());
    }
    public int getPorcentajeAltoTexto() {
        return porcentajeAltoTexto;
    }
    public void setPorcentajeAltoTexto(int porcentajeAltoTexto) {
        this.porcentajeAltoTexto = porcentajeAltoTexto;
        construirPanelCT(getLimitacion());
    }
    public void setEventoRaton(MouseAdapter mouse){
        addMouseListener(mouse);
        iuTitulo.addMouseListener(mouse);
        iuTitulo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iuAreaTexto.addMouseListener(mouse);
        iuAreaTexto.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}