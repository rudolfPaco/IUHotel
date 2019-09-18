/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.recursos;

import java.awt.image.BufferedImage;

/**
 *
 * @author hotel-felipez
 */
public class Archivo {
    
    private String nombreDocumento;
    private String urlDocumento;
    private String tipoDocumento;
    private BufferedImage buffered;

    public Archivo(BufferedImage imagenBuffered) {        
        this.buffered = imagenBuffered;
    }
    public String getNombreDocumento() {
        return nombreDocumento;
    }
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }
    public String getUrlDocumento() {
        return urlDocumento;
    }
    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public BufferedImage getBuffered() {
        return buffered;
    }
    public void setBuffered(BufferedImage buffered) {
        this.buffered = buffered;
    }
    @Override
    public String toString() {
        return "Archivo{" + "nombreDocumento=" + nombreDocumento + ", urlDocumento=" + urlDocumento + ", tipoDocumento=" + tipoDocumento + ", imagenBuffered=" + buffered + '}';
    }
}
