/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.paneles;

import com.aplicacionjava.www.campos.IUAreaTexto;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 *
 * @author rudolf
 */
public class IUPanelNota extends IUPanel{
    
    public IUEtiqueta iuTitulo;
    public IUAreaTexto iuTexto;
    
    private String titulo;
    private String texto;
    private int porcentajeAltoTitulo;
    private int porcentajeAnchoTexto;
    private int porcentajeAltoTexto;
 
    public IUPanelNota(String titulo, String texto, Limitacion limitacion, int porcentajeAltoTitulo, int porcentajeAnchoTexto, int porcentajeAltoTexto) {
        super(limitacion);        
        this.titulo = titulo;
        this.texto = texto;
        this.porcentajeAltoTitulo = porcentajeAltoTitulo;
        this.porcentajeAnchoTexto = porcentajeAnchoTexto;
        this.porcentajeAltoTexto = porcentajeAltoTexto;
        construirPanelNota(getLimitacion());
    }
    private void construirPanelNota(Limitacion limite){
        iuTitulo = new IUEtiqueta(titulo, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3, limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(porcentajeAltoTitulo)));
        iuTitulo.setForeground(new Color(120, 0, 0));
        iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        //iuTitulo.setBorder(new LineBorder(Color.yellow));
        iuTitulo.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(porcentajeAltoTitulo - porcentajeAltoTitulo/4)));
        add(iuTitulo);
        
        iuTexto = new IUAreaTexto(texto, new Limitacion(limite.getPorcentajeAncho(100 - porcentajeAnchoTexto)/2, 2*iuTitulo.getY() + iuTitulo.getHeight(), limite.getPorcentajeAncho(porcentajeAnchoTexto), limite.getPorcentajeAlto(porcentajeAltoTexto)));        
        iuTexto.setBorder(null);//iuTexto.setBorder(new LineBorder(Color.RED));
        iuTexto.setEditable(false);
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
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
        iuTexto.setText(texto);
    }    
    public void setColorTitulo(Color colorTitulo){
        iuTitulo.setForeground(colorTitulo);
    }
    public void setColorTexto(Color colorTexto){
        iuTexto.setForeground(colorTexto);
    }
    public int getPorcentajeAltoTitulo() {
        return porcentajeAltoTitulo;
    }
    public void setPorcentajeAltoTitulo(int porcentajeAltoTitulo) {
        this.porcentajeAltoTitulo = porcentajeAltoTitulo;
        construirPanelNota(getLimitacion());
    }
    public int getPorcentajeAltoTexto() {
        return porcentajeAltoTexto;
    }
    public void setPorcentajeAltoTexto(int porcentajeAltoTexto) {
        this.porcentajeAltoTexto = porcentajeAltoTexto;
        construirPanelNota(getLimitacion());
    }
    public int getPorcentajeAnchoTexto() {
        return porcentajeAnchoTexto;
    }
    public void setPorcentajeAnchoTexto(int porcentajeAnchoTexto) {
        this.porcentajeAnchoTexto = porcentajeAnchoTexto;
        construirPanelNota(getLimitacion());
    }
}