/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.paneles;

import com.aplicacionjava.www.campos.IUCampoPassword;
import com.aplicacionjava.www.campos.IUCampoTexto;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author neo
 */
public class IUPanelPT extends IUPanel{
    public IUEtiqueta iuTitulo;
    public IUCampoPassword iuTexto;
    
    private String titulo;
    private String texto;
    private int porcentajeAltoTitulo;
    private int porcentajeAltoTexto;
    
    public IUPanelPT(String titulo, String texto, Limitacion limitacion, int porcentajeAltoTitulo, int porcentajeAltoTexto) {
        super(limitacion);
        this.titulo = titulo;
        this.texto = texto;
        this.porcentajeAltoTitulo = porcentajeAltoTitulo;
        this.porcentajeAltoTexto = porcentajeAltoTexto;
        construirPanelCT(getLimitacion());
    }
    private void construirPanelCT(Limitacion limite){
        iuTitulo = new IUEtiqueta(titulo, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3, limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(porcentajeAltoTitulo)));
        iuTitulo.setForeground(new Color(120, 120, 120));
        iuTitulo.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(porcentajeAltoTitulo - porcentajeAltoTitulo/4)));
        add(iuTitulo);
        
        iuTexto = new IUCampoPassword(texto, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3 + limite.getPorcentajeAlto(porcentajeAltoTitulo), limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(porcentajeAltoTexto)));        
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
        texto = String.copyValueOf(iuTexto.getPassword());
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
        construirPanelCT(getLimitacion());
    }
    public int getPorcentajeAltoTexto() {
        return porcentajeAltoTexto;
    }
    public void setPorcentajeAltoTexto(int porcentajeAltoTexto) {
        this.porcentajeAltoTexto = porcentajeAltoTexto;
        construirPanelCT(getLimitacion());
    }
}
