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
import java.awt.Font;

/**
 *
 * @author rudolf
 */
public class IUPanelTC extends IUPanel{
    public IUEtiqueta iuTitulo;
    public IUCampoTexto iuTexto;
    
    private String titulo;
    private String texto;
    private final int porcentajeTitulo;
    private final int porcentajeTexto;
    
    /**
     * Interfaz de Usuario Panel Titulo y Texto. construye el panel y agrega una etiqueta titulo y la otra texto en forma horizontal. con porcentajes de ancho del titulo y texto correspondientemente.
     * @param limitacion de tipo Limitacion, que determina la posicion y el tamaño.
     * @param titulo cadena de caracteres para el titulo.
     * @param texto cadena de caracteres para el texto.
     * @param porcentajeTitulo un porcentaje para la anchura del titulo del ancho total.
     * @param porcentajeTexto un porcentaje para la anchura del texto del ancho total.
     */
    public IUPanelTC(Limitacion limitacion, String titulo, String texto, int porcentajeTitulo, int porcentajeTexto) {
        super(limitacion);
        this.titulo = titulo;
        this.texto = texto;
        this.porcentajeTexto = porcentajeTexto;
        this.porcentajeTitulo = porcentajeTitulo;
                
        construirPanel();
    }
    private void construirPanel(){
        iuTitulo = new IUEtiqueta(titulo, new Limitacion(limitacion.getPorcentajeAncho(100 - porcentajeTexto - porcentajeTitulo)/3, limitacion.getPorcentajeAlto(1), limitacion.getPorcentajeAncho(porcentajeTitulo), limitacion.getPorcentajeAlto(98)));
        iuTitulo.setForeground(new Color(120, 120, 120));
        add(iuTitulo);
        
        iuTexto = new IUCampoTexto(texto, new Limitacion(iuTitulo.getLimitacion().getTamX(2), limitacion.getPorcentajeAlto(1), limitacion.getPorcentajeAncho(porcentajeTexto), limitacion.getPorcentajeAlto(98)));        
        add(iuTexto);
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
        construirPanel();
    }
    public String getTexto() {
        texto = iuTexto.getText();
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
        construirPanel();
    }
    public void setColoresLetra(Color colorTitulo, Color colorTexto){
        iuTitulo.setForeground(colorTitulo);
        iuTexto.setForeground(colorTexto);
    }
    public void setPosicionHorizontal(int posicionTitulo, int posicionTexto){
        iuTitulo.setHorizontalAlignment(posicionTitulo);
        iuTexto.setHorizontalAlignment(posicionTexto);
    }
    public void setFuente(Font fuenteTitulo, Font fuenteTexto){
        iuTitulo.setFont(fuenteTitulo);
        iuTexto.setFont(fuenteTexto);
    }
}
