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
import javax.swing.SwingConstants;

/**
 *
 * @author rudolf
 */
public class IUPanelTTU extends IUPanelBD{
    
    public IUEtiqueta iuTitulo;
    public IUEtiqueta iuTexto;
    public IUEtiqueta iuUnidad;
    
    private String titulo;
    private String texto;
    private String unidad;
    private int porcentajeAltoTitulo;
    private int porcentajeAltoTexto;
    private int porcentajeAnchuraTexto;
    private int porcentajeAnchuraUnidad;
    
    /**
     * Interface de Usuario Panel de Titulo, Texto y Unidad de Medida, construye un panel el cual esta insertado una etiqueta titulo, otra etiqueta texto y una ultima etiqueta unidad de medida.
     * @param titulo determina el titulo en el componente.
     * @param texto determina el texto en el componente.
     * @param unidad determina la unidad en el componente.
     * @param limitacion determina la posicion y el tamaño del componente.
     * @param porcentajeAltoTitulo establece el porcentaje del alto para la etiqueta titulo en el panel.
     * @param porcentajeAltoTexto establece el porcentaje del alto para la etiqueta texto en el panel.
     * @param porcentajeAnchuraTexto establece el porcentaje de anchura para la etiqueta texto en el panel.
     * @param porcentajeAnchuraUnidad establece el porcentaje de anchura para la etiqueta unidad en el panel.
     */
    public IUPanelTTU(String titulo, String texto, String unidad, Limitacion limitacion, int porcentajeAltoTitulo, int porcentajeAltoTexto, int porcentajeAnchuraTexto, int porcentajeAnchuraUnidad) {
        super(limitacion);
        this.titulo = titulo;
        this.texto = texto;
        this.unidad = unidad;
        this.porcentajeAltoTitulo = porcentajeAltoTitulo;
        this.porcentajeAltoTexto = porcentajeAltoTexto;
        this.porcentajeAnchuraTexto = porcentajeAnchuraTexto;
        this.porcentajeAnchuraUnidad = porcentajeAnchuraUnidad;
        construirPanelTTU(getLimitacion());
    }
    private void construirPanelTTU(Limitacion limite){
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        iuTitulo = new IUEtiqueta(titulo, new Limitacion(limite.getPorcentajeAncho(2), limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3, limite.getPorcentajeAncho(96), limite.getPorcentajeAlto(porcentajeAltoTitulo)));
        iuTitulo.setForeground(new Color(120, 0, 0));
        iuTitulo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iuTitulo.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(porcentajeAltoTitulo - porcentajeAltoTitulo/2)));
        iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(iuTitulo);
        
        iuTexto = new IUEtiqueta(texto, new Limitacion(limite.getPorcentajeAncho(100 - porcentajeAnchuraTexto - porcentajeAnchuraUnidad)/3, limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3 + limite.getPorcentajeAlto(porcentajeAltoTitulo), limite.getPorcentajeAncho(porcentajeAnchuraTexto), limite.getPorcentajeAlto(porcentajeAltoTexto)));
        //iuTexto.setForeground(Color.LIGHT_GRAY);
        iuTexto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iuTexto.setHorizontalAlignment(SwingConstants.RIGHT);
        iuTexto.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(porcentajeAltoTitulo - porcentajeAltoTitulo/5)));
        add(iuTexto);
        
        iuUnidad = new IUEtiqueta(unidad, new Limitacion(limite.getPorcentajeAncho(100 - porcentajeAnchuraTexto - porcentajeAnchuraUnidad)/3 + limite.getPorcentajeAncho(porcentajeAnchuraTexto), limite.getPorcentajeAlto(100 - porcentajeAltoTitulo - porcentajeAltoTexto)/3 + limite.getPorcentajeAlto(porcentajeAltoTitulo), limite.getPorcentajeAncho(porcentajeAnchuraUnidad), limite.getPorcentajeAlto(porcentajeAltoTexto)));
        iuUnidad.setForeground(Color.LIGHT_GRAY);
        iuUnidad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iuUnidad.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(porcentajeAltoTitulo - porcentajeAltoTitulo/5)));
        iuUnidad.setHorizontalAlignment(SwingConstants.RIGHT);
        add(iuUnidad);
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
    public String getUnidad() {
        return unidad;
    }
    public void setUnidad(String unidad) {
        this.unidad = unidad;
        iuUnidad.setText(unidad);
    }
    public int getPorcentajeAltoTitulo() {
        return porcentajeAltoTitulo;
    }
    public void setPorcentajeAltoTitulo(int porcentajeAltoTitulo) {
        this.porcentajeAltoTitulo = porcentajeAltoTitulo;
        construirPanelTTU(getLimitacion());
    }
    public int getPorcentajeAltoTexto() {
        return porcentajeAltoTexto;
    }
    public void setPorcentajeAltoTexto(int porcentajeAltoTexto) {
        this.porcentajeAltoTexto = porcentajeAltoTexto;
        construirPanelTTU(getLimitacion());
    }
    public int getPorcentajeAnchuraUnidad() {
        return porcentajeAnchuraUnidad;
    }
    public void setPorcentajeAnchuraUnidad(int porcentajeAnchuraUnidad) {
        this.porcentajeAnchuraUnidad = porcentajeAnchuraUnidad;
        construirPanelTTU(getLimitacion());
    }
    public int getPorcentajeAnchuraTexto() {
        return porcentajeAnchuraTexto;
    }
    public void setPorcentajeAnchuraTexto(int porcentajeAnchuraTexto) {
        this.porcentajeAnchuraTexto = porcentajeAnchuraTexto;
        construirPanelTTU(limitacion);
    }
    public void setPosicionHorizontal(int posicionTitulo, int posicionTexto, int posicionUnidad){
        iuTitulo.setHorizontalAlignment(posicionTitulo);
        iuTexto.setHorizontalAlignment(posicionTexto);
        iuUnidad.setHorizontalAlignment(posicionUnidad);        
    }
    public void setColoresForeground(Color colorTitulo, Color colorTexto, Color colorUnidad){
        iuTitulo.setForeground(colorTitulo);
        iuTexto.setForeground(colorTexto);
        iuUnidad.setForeground(colorUnidad);        
    }    
    public void addEventoRaton(MouseAdapter evento){
        addMouseListener(evento);
        iuTitulo.addMouseListener(evento);
        iuTexto.addMouseListener(evento);
        iuUnidad.addMouseListener(evento);
    }
}
