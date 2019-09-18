/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.paneles;

import com.aplicacionjava.www.campos.IUAreaTexto;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author rudolf
 */
public class IUPanelITT extends IUPanelBD{
    public IUEtiquetaI iuUrl;
    public IUEtiqueta iuTitulo;
    public IUAreaTexto iuTexto;
    
    private String url;
    private String titulo;
    private String texto;
    private int porcentajeImagen;
    private int porcentajeAnchoTT;
    private int porcentajeAltoTitulo;
    private int porcentajeAltoTexto;
    
    /**
     * Interface de Usuario Panel de Titulo, Texto y Unidad de Medida, construye un panel el cual esta insertado una etiqueta titulo, otra etiqueta texto y una ultima etiqueta unidad de medida.
     * @param url determina el titulo en el componente.
     * @param titulo determina el texto en el componente.
     * @param texto determina la unidad en el componente.
     * @param limitacion determina la posicion y el tamaño del componente.
     * @param porcentajeAltoImagen establece el porcentaje del alto para la etiqueta titulo en el panel.
     * @param porcentajeAnchoTT establece el porcentaje del alto para la etiqueta texto en el panel.
     * @param porcentajeAltoTitulo establece el porcentaje de anchura para la etiqueta texto en el panel.
     * @param porcentajeAltoTexto establece el porcentaje de anchura para la etiqueta unidad en el panel.
     */
    public IUPanelITT(String url, String titulo, String texto, Limitacion limitacion, int porcentajeAltoImagen, int porcentajeAnchoTT, int porcentajeAltoTitulo, int porcentajeAltoTexto) {
        super(limitacion);
        this.url = url;
        this.titulo = titulo;
        this.texto = texto;
        this.porcentajeImagen = porcentajeAltoImagen;
        this.porcentajeAnchoTT = porcentajeAnchoTT;
        this.porcentajeAltoTitulo = porcentajeAltoTitulo;
        this.porcentajeAltoTexto = porcentajeAltoTexto;
        construirPanelTTU(getLimitacion());
    }
    private void construirPanelTTU(Limitacion limite){
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        iuUrl = new IUEtiquetaI(url, new Limitacion((limite.getPorcentajeAncho(100 - porcentajeAnchoTT) - limite.getPorcentajeAlto(porcentajeImagen))/3, limite.getPorcentajeAlto(100 - porcentajeImagen)/2, limite.getPorcentajeAlto(porcentajeImagen), limite.getPorcentajeAlto(porcentajeImagen)));
        iuUrl.setCursor(new Cursor(Cursor.HAND_CURSOR));        
        add(iuUrl);
        
        iuTitulo = new IUEtiqueta(titulo, new Limitacion(iuUrl.getLimitacion().getTamX(2), iuUrl.getLimitacion().getY(), limite.getPorcentajeAncho(porcentajeAnchoTT), limite.getPorcentajeAlto(porcentajeAltoTitulo)));
        iuTitulo.setForeground(new Color(120, 0, 0));
        iuTitulo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        iuTitulo.setFont(new Font("Verdana", Font.PLAIN, 20));//limite.getPorcentajeAlto(porcentajeAltoTitulo - porcentajeAltoTitulo/10)
        //iuTitulo.setBorder(new LineBorder(Color.yellow));
        add(iuTitulo);
        
        iuTexto = new IUAreaTexto(texto, new Limitacion(iuUrl.getLimitacion().getTamX(2), iuTitulo.getLimitacion().getTamY(1), limite.getPorcentajeAncho(porcentajeAnchoTT), limite.getPorcentajeAlto(porcentajeAltoTexto)));        
        iuTexto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        iuTexto.setBorder(null);
        iuTexto.setEditable(false);
        iuTexto.setFont(new Font("Verdana", Font.PLAIN, 12));
        add(iuTexto);
    }

    public String getTitulo() {
        return url;
    }
    public void setTitulo(String titulo) {
        this.url = titulo;
        iuUrl.setText(titulo);
    }
    public String getTexto() {
        return titulo;
    }
    public void setTexto(String texto) {
        this.titulo = texto;
        iuTitulo.setText(texto);
    }
    public String getUnidad() {
        return texto;
    }
    public void setUnidad(String unidad) {
        this.texto = unidad;
        iuTexto.setText(unidad);
    }
    public int getPorcentajeAltoTitulo() {
        return porcentajeImagen;
    }
    public void setPorcentajeAltoTitulo(int porcentajeAltoTitulo) {
        this.porcentajeImagen = porcentajeAltoTitulo;
        construirPanelTTU(getLimitacion());
    }
    public int getPorcentajeAltoTexto() {
        return porcentajeAnchoTT;
    }
    public void setPorcentajeAltoTexto(int porcentajeAltoTexto) {
        this.porcentajeAnchoTT = porcentajeAltoTexto;
        construirPanelTTU(getLimitacion());
    }
    public int getPorcentajeAnchuraUnidad() {
        return porcentajeAltoTexto;
    }
    public void setPorcentajeAnchuraUnidad(int porcentajeAnchuraUnidad) {
        this.porcentajeAltoTexto = porcentajeAnchuraUnidad;
        construirPanelTTU(getLimitacion());
    }
    public int getPorcentajeAnchuraTexto() {
        return porcentajeAltoTitulo;
    }
    public void setPorcentajeAnchuraTexto(int porcentajeAnchuraTexto) {
        this.porcentajeAltoTitulo = porcentajeAnchuraTexto;
        construirPanelTTU(limitacion);
    }
    public void setPosicionHorizontal(int posicionTitulo, int posicionTexto, int posicionUnidad){
        iuUrl.setHorizontalAlignment(posicionTitulo);
        iuTitulo.setHorizontalAlignment(posicionTexto);        
    }
    public void setColoresForeground(Color colorTitulo, Color colorTexto, Color colorUnidad){
        iuUrl.setForeground(colorTitulo);
        iuTitulo.setForeground(colorTexto);
        iuTexto.setForeground(colorUnidad);        
    }    
    public void addEventoRaton(MouseAdapter evento){
        addMouseListener(evento);
        iuUrl.addMouseListener(evento);
        iuTitulo.addMouseListener(evento);
        iuTexto.addMouseListener(evento);
    }
}
